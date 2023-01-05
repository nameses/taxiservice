package DAO.helper;

import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {

    protected abstract T buildEntity(ResultSet resultSet);
    protected abstract void setStatement(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected final ConnectionPool connectionPool = ConnectionPool.getInstance();

    protected List<T> selectList(String query, List<String> params) {
        Connection connection = connectionPool.getConnection();
        List<T> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = prepareStatement(connection, query, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = buildEntity(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    protected T selectEntityByID(String query, Integer id) {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return buildEntity(resultSet);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    protected Boolean executeQuery(String query, List<String> params) {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = this.prepareStatement(connection, query, params);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    protected T getEntity(String query, List<String> params) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = this.prepareStatement(connection, query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return this.buildEntity(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    protected PreparedStatement prepareStatement(Connection connection, String query, List<String> params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (params != null) {
                int index = 1;
                for (String p : params) {
                    preparedStatement.setObject(index++, (isNumeric(p) ? Integer.valueOf(p) : p));
                }
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected PreparedStatement prepareStatement(Connection connection, String query, T entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            setStatement(preparedStatement,entity);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
