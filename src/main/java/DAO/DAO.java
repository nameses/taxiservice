package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static utils.JDBCUtil.getConnection;

public abstract class DAO<T> {
    protected abstract List<String> getParameters(T entity);
    protected abstract T buildEntity(ResultSet resultSet);
    protected Boolean executeQuery(String query, List<String> parameters){
        try (PreparedStatement preparedStatement = prepareStatement(query, parameters)) {
            return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected T getEntity(String query, List<String> params){
        try(PreparedStatement preparedStatement = prepareStatement(query, params);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return buildEntity(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected Boolean insert(String query, T entity){
        List<String> params = getParameters(entity);
        return executeQuery(query, params);
    }

    private PreparedStatement prepareStatement(String sqlQuery, List<String> parameters){
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
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
