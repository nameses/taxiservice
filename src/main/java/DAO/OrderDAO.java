package DAO;

import entity.Taxi;
import entity.TaxiOrder;
import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DAO<TaxiOrder> {
    private static final String SELECT_ALL =
            "SELECT orderid,orderopened,orderaccepted,cost,\"licensePlate\",username from taxiorder " +
                    "JOIN taxi ON taxi.taxiid=taxiorder.taxiid " +
                    "JOIN useraccount ON useraccount.userid=taxiorder.userid";

    public List<TaxiOrder> selectAllByString(String orderByString, String orderBySort,
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
    protected List<String> getParameters(TaxiOrder order) {
        String orderOpened = String.valueOf(order.getOrderOpened());
        String orderAccepted = String.valueOf(order.getOrderAccepted());
        String cost = String.valueOf(order.getCost());
        return List.of(orderOpened, orderAccepted, cost);
    }

    @Override
    protected TaxiOrder buildEntity(ResultSet resultSet) {
        try {
            TaxiOrder order = new TaxiOrder();
            order.setOrderID(resultSet.getInt("orderid"));
            order.setOrderOpened(resultSet.getTimestamp("orderopened"));
            order.setOrderAccepted(resultSet.getTimestamp("orderaccepted"));
            order.setCost(resultSet.getInt("cost"));
            order.setStatus(resultSet.getString("status"));
            order.getUser().setUsername(resultSet.getString("username"));
            order.getTaxi().setLicensePlate(resultSet.getString("licensePlate"));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
