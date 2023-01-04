package DAO;

import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DAO<Order> {
    private static final String SELECT_ALL =
            "SELECT orderid,orderopened,orderaccepted,cost,\"licensePlate\",username from taxiorder " +
                    "JOIN taxi ON taxi.taxiid=taxiorder.taxiid " +
                    "JOIN useraccount ON useraccount.userid=taxiorder.userid";

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
    protected List<String> getParameters(Order order) {
        String orderOpened = String.valueOf(order.getOrderOpened());
        String orderAccepted = String.valueOf(order.getOrderAccepted());
        String cost = String.valueOf(order.getCost());
        return List.of(orderOpened, orderAccepted, cost);
    }

    @Override
    protected Order buildEntity(ResultSet resultSet) {
        try {
            Order order = new Order();
            order.setOrderID(resultSet.getInt("orderid"));
            order.setOrderOpened(resultSet.getTimestamp("orderopened"));
            order.setOrderAccepted(resultSet.getTimestamp("orderaccepted"));
            order.setCost(resultSet.getInt("cost"));
            order.setStatus(resultSet.getString("status"));
            order.getUsername().setUsername(resultSet.getString("username"));
            order.getTaxi().setLicensePlate(resultSet.getString("licensePlate"));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
