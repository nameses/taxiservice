package DAO;

import entity.Taxi;
import pool.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxiDAO extends DAO<Taxi> {
    private static final String SELECT_ALL =
            "SELECT * FROM taxi";
    private static final String UPDATE_STATUS =
            "UPDATE taxi SET status=? where taxiid=?";
    private static final String INSERT =
            "INSERT INTO taxi(status,capacity,category,fare,\"licensePlate\",\"driverName\") VALUES(?,?,?,?,?,?)";
    private final static String DELETE_BY_ID =
            "DELETE FROM taxi WHERE taxiid=?";
    public void deleteTaxi(Integer id){
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean insertTaxi(Taxi taxi){
        return insert(INSERT,taxi);
    }

    public void updateStatus(Integer id, String toStatus) {
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS);
            preparedStatement.setString(1, toStatus);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Taxi> selectAllByString(String orderByString, String orderBySort) {
        if (orderByString == null || orderBySort == null) {
            return selectList(SELECT_ALL,null);
        } else return selectList(SELECT_ALL + " ORDER BY " + orderByString + " " + orderBySort,null);
    }

    @Override
    protected List<String> getParameters(Taxi taxi) {
        String status = taxi.getStatus();
        String capacity = taxi.getCapacity();
        String category = taxi.getCategory();
        String fare = taxi.getFare().toString();
        String licensePlate = taxi.getLicensePlate();
        String driverName = taxi.getDriverName();
        return List.of(status, capacity, category, fare, licensePlate, driverName);
    }

    @Override
    protected Taxi buildEntity(ResultSet resultSet) {
        try {
            Taxi taxi = new Taxi();
            taxi.setTaxiID(resultSet.getInt("taxiid"));
            taxi.setStatus(resultSet.getString("status"));
            taxi.setCapacity(resultSet.getString("capacity"));
            taxi.setCategory(resultSet.getString("category"));
            taxi.setFare(resultSet.getInt("fare"));
            taxi.setLicensePlate(resultSet.getString("licensePlate"));
            taxi.setDriverName(resultSet.getString("driverName"));
            return taxi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected PreparedStatement prepareStatement(Connection connection, String query, List<String> parameters) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (parameters != null) {
                int index = 1;
                for (String p : parameters) {
                    preparedStatement.setObject(index++, (isNumeric(p)?Integer.valueOf(p):p));
                }
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
