package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import exceptions.DAOException;
import models.DTO.DriverDTO;
import models.DTO.OrderDTO;
import models.DTO.SortFilterDTO;
import models.DTO.TaxiDTO;
import models.converters.OrderConverter;
import models.entity.Driver;
import models.entity.Order;
import models.entity.Taxi;
import models.entity.enums.OrderStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO extends DAO<Order> {
    private static final String SELECT_ALL_BY_STATUS =
            "SELECT * from \"order\" JOIN route ON route.orderid=\"order\".orderid " +
                    "WHERE status=?::orderstatus AND \"carCapacity\"<=? " +
                    "AND carcategory<=?::carcategory ";
    private static final String INSERT =
            "INSERT INTO \"order\"(clientid,orderopened,\"carCapacity\",carcategory,status) " +
                    "VALUES(?,?,?,?::carcategory,?::orderstatus)";
    private static final String SELECT_BY_ID =
            "SELECT * from \"order\" WHERE orderid=?";
    private static final String UPDATE_DRIVER_ID =
            "UPDATE \"order\" SET driverid=? WHERE \"order\".orderid=?";
    private static final String ACCEPT_DRIVER =
            "UPDATE \"order\" SET orderaccepted=?, cost=?, status=?::orderstatus WHERE \"order\".orderid=?";
    private static final String DECLINE_DRIVER =
            "UPDATE \"order\" SET driverid=NULL, status=?::orderstatus WHERE \"order\".orderid=?";
    private static final String UPDATE_ENUM_TO_CANCELED_TO_ALL_EXCEPT_DONE =
            "UPDATE \"order\" SET status=?::orderstatus WHERE status!=?::orderstatus " +
                    "AND status!=?::orderstatus";
    private static final String CANCEL_DRIVER_FROM_ORDER =
            "UPDATE \"order\" SET driverid=NULL WHERE \"order\".orderid=?";
    private static final String UPDATE_ENUM_TO_STATUS =
            "UPDATE \"order\" SET status=?::orderstatus WHERE \"order\".orderid=?";
    public Boolean cancelDriverFromOrder(Integer orderID) throws DAOException {
        return executeQuery(CANCEL_DRIVER_FROM_ORDER, orderID);
    }
    public Boolean deactivateAllOrders() throws DAOException {
        return executeQuery(UPDATE_ENUM_TO_CANCELED_TO_ALL_EXCEPT_DONE,
                OrderStatus.canceled,
                OrderStatus.canceled,
                OrderStatus.completed);
    }
    public Boolean acceptDriver(Integer orderID, Integer cost) throws DAOException {
        Timestamp orderAccepted = new Timestamp(System.currentTimeMillis());
        return this.executeQuery(ACCEPT_DRIVER, orderAccepted, cost, "on route", orderID);
    }
    public Boolean declineDriver(Integer orderID) throws DAOException {
        return this.executeQuery(DECLINE_DRIVER, OrderStatus.processing, orderID);
    }

    public OrderDTO selectByID(Integer id) throws DAOException {
        return OrderConverter.toDTO(select(SELECT_BY_ID, id));
    }

    public Boolean updateDriverID(Integer orderID, Integer driverID) throws DAOException {
        return this.executeQuery(UPDATE_DRIVER_ID, driverID, orderID);
    }

    public Boolean updateEnumToStatus(Integer id, OrderStatus orderStatus) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement =
                    this.prepareStatement(connection,
                            UPDATE_ENUM_TO_STATUS,
                            List.of(orderStatus.toString(), String.valueOf(id)));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    public List<OrderDTO> selectList(OrderStatus orderStatus, Driver driver,
                                     Taxi taxi, SortFilterDTO sortFilterDTO) throws DAOException {
        StringBuilder sql_statement = new StringBuilder(SELECT_ALL_BY_STATUS);
        List<Object> filterElements = new ArrayList<>();
        // forming statement with filtering and sorting
        if (sortFilterDTO.getFilterMap() != null) {
            sortFilterDTO.getFilterMap()
                    .forEach((key, value) -> {
                        filterElements.add(value);
                        if (key.equals("maxCapacity"))
                            sql_statement.append("AND " + "\"carCapacity\"<=? ");
                        else
                            sql_statement.append("AND ").append(key).append("=? ");
                        if (key.equals("carcategory"))
                            sql_statement.append("::carcategory ");
                    });
        }
        //order by processing
        if (sortFilterDTO.getOrderByMap() != null) {
            sortFilterDTO.getOrderByMap().forEach((key, value) ->
                    sql_statement.append(" ORDER BY ")
                            .append(key)
                            .append(" ")
                            .append(value));
        }
        //get list of orders from DB
        List<Order> orders = this.selectList(
                sql_statement.toString(),
                orderStatus,
                taxi.getCapacity(),
                taxi.getCategory(),
                filterElements.toArray()
        );

        return orders.stream()
                .map(OrderConverter::toDTO)
                .toList();
    }

    public OrderDTO insert(Order order) throws DAOException {
        Integer id = insert(INSERT, order);
        if (id != null) {
            OrderDTO orderDTO = OrderConverter.toDTO(order);
            orderDTO.setOrderID(id);
            orderDTO.setSuccess(true);
            return orderDTO;
        } else {
            return new OrderDTO(false, "Try again.");
        }
    }

//    public List<Order> selectAllByString(String orderByString, String orderBySort,
//                                         String filterBy, String filterValue) {
//        String resultQuery = SELECT_ALL;
//        List<String> params = new ArrayList<>();
//        if (filterBy != null && filterValue != null) {
//            if(filterBy.equals("licensePlate")) filterBy="\"licensePlate\"";
//            resultQuery += " WHERE " + filterBy + "=?";
//            params.add(filterValue);
//        }
//        if (orderByString != null && orderBySort !=null) {
//            resultQuery += " ORDER BY " + orderByString + " " + orderBySort;
//        }
//        return selectList(resultQuery,params);
//    }


    @Override
    protected Order buildEntity(ResultSet resultSet) throws DAOException {
        return EntityBuilder.buildOrder(resultSet);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Order entity) throws SQLException {
        if (entity.getOrderStatus().equals(OrderStatus.processing)) {
            preparedStatement.setInt(1, entity.getClientID());
            preparedStatement.setTimestamp(2, entity.getOrderOpened());
            preparedStatement.setInt(3, entity.getCarCapacity());
            preparedStatement.setString(4, entity.getCarCategory().toString());
            preparedStatement.setString(5, entity.getOrderStatus().toString());
        } else {
            preparedStatement.setInt(1, entity.getClientID());
            preparedStatement.setInt(2, entity.getDriverID());
            preparedStatement.setTimestamp(3, entity.getOrderOpened());
            preparedStatement.setTimestamp(4, entity.getOrderAccepted());
            preparedStatement.setInt(5, entity.getCost());
            preparedStatement.setInt(6, entity.getCarCapacity());
            preparedStatement.setString(7, entity.getOrderStatus().toString());
            preparedStatement.setString(8, entity.getCarCategory().toString());
        }

    }
}
