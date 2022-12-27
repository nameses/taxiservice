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
            "SELECT * FROM taxiorder";

    public List<TaxiOrder> selectAllByString(String orderByString, String orderBySort) {
        if (orderByString == null || orderBySort == null) {
            return selectList(SELECT_ALL);
        } else return selectList(SELECT_ALL + " ORDER BY " + orderByString + " " + orderBySort);
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
            order.setUserID(resultSet.getInt("userid"));
            order.setTaxiID(resultSet.getInt("taxiid"));
            order.setOrderOpened(resultSet.getTimestamp("orderopened"));
            order.setOrderAccepted(resultSet.getTimestamp("orderaccepted"));
            order.setCost(resultSet.getInt("cost"));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
