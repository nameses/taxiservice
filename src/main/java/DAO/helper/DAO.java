package DAO.helper;

import exceptions.DAOException;
import models.DTO.SortFilterDTO;
import pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DAO<T> {

    protected abstract T buildEntity(ResultSet resultSet) throws DAOException;

    protected abstract void setStatement(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected final ConnectionPool connectionPool = ConnectionPool.getInstance();

    protected List<T> selectList(String query, Object... objects) throws DAOException {
        Connection connection = connectionPool.getConnection();
        ArrayList<T> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = prepareStatement(connection, query, objects);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T entity = buildEntity(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    protected T select(String query, Object... params) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = this.prepareStatement(connection, query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return this.buildEntity(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

//    protected T select(String query, List<String> params) throws DAOException {
//        Connection connection = connectionPool.getConnection();
//        try (PreparedStatement preparedStatement = this.prepareStatement(connection, query, params);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            if (resultSet.next()) {
//                return this.buildEntity(resultSet);
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            connectionPool.returnConnection(connection);
//        }
//    }

    protected Integer insert(String query, Object... objects) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = this.prepareStatement(connection, query, objects);
            boolean res = preparedStatement.executeUpdate() > 0;
            if (!res) return null;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return null;
    }

    protected Integer insert(String query, T entity) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = this.prepareStatement(connection, query, entity);
            boolean res = preparedStatement.executeUpdate() > 0;
            if (!res) return null;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return null;
    }

    protected Boolean executeQuery(String query, Object... params) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = this.prepareStatement(connection, query, params);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    protected PreparedStatement prepareStatement(Connection connection, String query, T entity) throws DAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setStatement(preparedStatement, entity);
            return preparedStatement;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    protected PreparedStatement prepareStatement(Connection connection, String query,
                                                 Object... objects) throws DAOException {
        //can pass array(Object[])
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            if (objects != null) {
                int index = 1;
                for (Object o : objects) {
                    if (o instanceof Object[]) {
                        for (Object obj : (Object[]) o) {
                            if (obj instanceof Enum) obj = obj.toString();
                            preparedStatement.setObject(index++, obj);
                        }
                    } else {
                        if (o instanceof Enum) o = o.toString();
                        preparedStatement.setObject(index++, o);
                    }
                }
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    protected PreparedStatement prepareStatement(Connection connection, String query, List<String> params) throws DAOException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (params != null) {
                int index = 1;
                for (String p : params) {
                    preparedStatement.setObject(index++, (isNumeric(p) ? Integer.valueOf(p) : p));
                }
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }


    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
