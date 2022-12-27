package DAO;

import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {
    protected abstract List<String> getParameters(T entity);

    protected abstract T buildEntity(ResultSet resultSet);

    protected final ConnectionPool connectionPool = ConnectionPool.getInstance();

    protected List<T> selectList(String query) {
        Connection connection = connectionPool.getConnection();
        List<T> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = buildEntity(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.returnConnection(connection);
        }
    }
    protected T selectEntityByID(String query, Integer id){
        Connection connection = connectionPool.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return buildEntity(resultSet);
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    protected Boolean insert(String query, T entity) {
        List<String> params = this.getParameters(entity);
        return this.executeQuery(query, params);
    }

    protected Boolean executeQuery(String query, List<String> params) {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = this.prepareStatement(connection, query, params);
            return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
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

    private PreparedStatement prepareStatement(Connection connection, String query, List<String> parameters) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (parameters != null) {
                int index = 1;
                for (String parameter : parameters)
                    preparedStatement.setObject(index++, parameter);
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
