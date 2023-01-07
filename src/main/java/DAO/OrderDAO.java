package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.Order;

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
        return;
    }
}
