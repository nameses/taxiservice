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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO extends DAO<Order> {
    private static final String SELECT_ALL_BY_STATUS =
            "SELECT * from \"order\" WHERE status=?::orderstatus " +
                    "AND \"carCapacity\"<=? AND carcategory<=?::carcategory ";
    private static final String INSERT =
            "INSERT INTO \"order\"(clientid,orderopened,\"carCapacity\",carcategory,status) " +
                    "VALUES(?,?,?,?::carcategory,?::orderstatus)";
    private static final String UPDATE_ENUM_TO_STATUS =
            "UPDATE \"order\" SET status=?::orderstatus WHERE \"order\".orderid=?";

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
        // forming statement with filtering and sorting
        if (sortFilterDTO.getFilter() != null) {
            sql_statement.append(" AND ").append(sortFilterDTO.getFilter()).append(" ");
        }
        if (sortFilterDTO.getOrderBy() != null) {
            sql_statement.append(sortFilterDTO.getOrderBy()).append(" ");
        }

        List<Order> orders = this.selectList(
                sql_statement.toString(),
                orderStatus,
                taxi.getCapacity(),
                taxi.getCategory()
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
