package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DAO<Order> {
    private static final String SELECT_ALL =
            "SELECT orderid,orderopened,orderaccepted,cost,\"licensePlate\",username from Order " +
                    "JOIN driver ON driver.driverid=\"Order\".driverid " +
                    "JOIN taxi ON taxi.taxiid=driver.taxiid " +
                    "JOIN client ON \"Order\".clientid=client.clientid " +
                    "JOIN \"User\" ON client.userid=\"User\".userid ";

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
}
