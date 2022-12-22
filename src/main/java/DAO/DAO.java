package DAO;

import pool.ConnectionCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    protected abstract List<String> getParameters(T entity);

    protected abstract T buildEntity(ResultSet resultSet);

    protected Connection connection;

    protected void initConnection() {
        try {
            this.connection = ConnectionCreator.createConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void rollback() {
        try {
            this.connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Boolean executeQuery(String query, List<String> parameters) {
        try (PreparedStatement preparedStatement = this.prepareStatement(query, parameters)) {
            return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected T getEntity(String query, List<String> params) {
        try (PreparedStatement preparedStatement = this.prepareStatement(query, params);
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

    private PreparedStatement prepareStatement(String query, List<String> parameters) {
        try {
            Connection connection = ConnectionCreator.createConnection();
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
