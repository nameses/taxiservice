package DAO;

import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    protected abstract List<String> getParameters(T entity);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    protected abstract T buildEntity(ResultSet resultSet);


    protected Boolean executeQuery(String query, List<String> params) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = this.prepareStatement(connection, query, params)) {
            return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected T getEntity(String query, List<String> params) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = this.prepareStatement(connection, query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return this.buildEntity(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean insert(String query, T entity) {
        List<String> params = this.getParameters(entity);
        return this.executeQuery(query, params);
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
