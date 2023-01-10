package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.Order;
import entity.enums.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DAO<Order> {
    private static final String SELECT_ALL =
            "SELECT orderid,orderopened,orderaccepted,cost,\"licensePlate\",username from order " +
                    "JOIN driver ON driver.driverid=\"order\".driverid " +
                    "JOIN taxi ON taxi.taxiid=driver.taxiid " +
                    "JOIN client ON \"order\".clientid=client.clientid " +
                    "JOIN \"user\" ON client.userid=\"user\".userid ";
    private static final String INSERT =
            "INSERT INTO \"order\"(clientid,orderopened,\"carCapacity\",carcategory,status,routeid) " +
                    "VALUES(?,?,?,?::carcategory,?::orderstatus,?)";
    private static final String UPDATE_ENUM_TO_STATUS =
            "UPDATE \"order\" SET status=?::orderstatus WHERE \"order\".orderid=?";
    public Boolean updateEnumToStatus(Integer id, OrderStatus orderStatus){
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement =
                    this.prepareStatement(connection,
                            UPDATE_ENUM_TO_STATUS,
                            List.of(orderStatus.toString(),String.valueOf(id)));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
    public Integer insert(Order order){
        return insert(INSERT,order);
    }
    public List<Order> selectAllByString(String orderByString, String orderBySort,
                                         String filterBy, String filterValue) {
        String resultQuery = SELECT_ALL;
        List<String> params = new ArrayList<>();
        if (filterBy != null && filterValue != null) {
            if(filterBy.equals("licensePlate")) filterBy="\"licensePlate\"";
            resultQuery += " WHERE " + filterBy + "=?";
            params.add(filterValue);
        }
        if (orderByString != null && orderBySort !=null) {
            resultQuery += " ORDER BY " + orderByString + " " + orderBySort;
        }
        return selectList(resultQuery,params);
    }


    @Override
    protected Order buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildOrder(resultSet);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Order entity) throws SQLException {
        preparedStatement.setInt(1, entity.getClientID());
        preparedStatement.setTimestamp(2, entity.getOrderOpened());
        preparedStatement.setInt(3, entity.getCarCapacity());
        preparedStatement.setString(4, entity.getCarCategory().toString());
        preparedStatement.setString(5, entity.getOrderStatus().toString());
        preparedStatement.setInt(6, entity.getRouteID());
    }
}
