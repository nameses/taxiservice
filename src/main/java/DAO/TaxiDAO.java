package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.Taxi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaxiDAO extends DAO<Taxi> {
    private static final String SELECT_ALL =
            "SELECT * FROM taxi";
    private static final String UPDATE_STATUS =
            "UPDATE taxi SET status=? where taxiid=?";
    private static final String INSERT =
            "INSERT INTO taxi(capacity,category,fare,\"licensePlate\",\"driverName\") VALUES(?,?,?,?,?,?)";
    private final static String DELETE_BY_ID =
            "DELETE FROM taxi WHERE taxiid=?";

//    public void deleteTaxi(Integer id) {
//        try {
//            Connection connection = connectionPool.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//            connectionPool.returnConnection(connection);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Boolean insertTaxi(Taxi taxi) {
//        return this.executeQuery(INSERT,
//                List.of(
//                        String.valueOf(taxi.getCapacity()),
//                        String.valueOf(taxi.getCategory()),
//                        String.valueOf(taxi.getFare()),
//                        taxi.getLicensePlate()
//                ));
//    }
//
//    public List<Taxi> selectAllByString(String orderByString, String orderBySort) {
//        if (orderByString == null || orderBySort == null) {
//            return selectList(SELECT_ALL, null);
//        } else return selectList(SELECT_ALL + " ORDER BY " + orderByString + " " + orderBySort, null);
//    }


    @Override
    protected Taxi buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildTaxi(resultSet);
    }



}
