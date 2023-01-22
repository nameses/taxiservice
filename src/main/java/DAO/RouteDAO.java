package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import exceptions.DAOException;
import models.DTO.RouteDTO;
import models.converters.RouteConverter;
import models.entity.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteDAO extends DAO<Route> {
    private static final String UPDATE_ORDERID_BY_ROUTEID =
            "UPDATE route SET orderid=? WHERE routeid=?";
    private static final String INSERT =
            "INSERT INTO route(startmarker,finalmarker,length) VALUES(?,?,?)";

    public Map<Integer, RouteDTO> selectRouteByOrderID(List<Integer> orderIDs) throws DAOException {
        Map<Integer,RouteDTO> result = new HashMap<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM route WHERE orderid=any(?)")) {

            Array array = connection.createArrayOf("integer", orderIDs.toArray());
            statement.setArray(1, array);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Route route = buildEntity(rs);
                result.put(route.getOrderID(), RouteConverter.toDTO(route));
            }
            rs.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.returnConnection(connection);
        }
        return result;
    }


    public RouteDTO updateOrderIDbyRouteID(Route route) throws DAOException {
        return new RouteDTO(executeQuery(UPDATE_ORDERID_BY_ROUTEID,
                List.of(
                        String.valueOf(route.getOrderID()),
                        String.valueOf(route.getRouteID())
                )));
    }

    public Integer insert(Route route) throws DAOException {
        return insert(INSERT, route);
    }

    @Override
    protected Route buildEntity(ResultSet resultSet) throws DAOException {
        return EntityBuilder.buildRoute(resultSet);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Route entity) throws SQLException {
        Connection connection = connectionPool.getConnection();
        preparedStatement.setArray(1,
                connection.createArrayOf("DOUBLE", entity.getStartMarker()));
        preparedStatement.setArray(2,
                connection.createArrayOf("DOUBLE", entity.getFinalMarker()));
        preparedStatement.setInt(3, entity.getLength());
        connectionPool.returnConnection(connection);
    }

}
