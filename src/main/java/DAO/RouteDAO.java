package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.Order;
import entity.Route;
import entity.User.User;

import java.sql.*;
import java.util.List;

public class RouteDAO extends DAO<Route> {
    private static final String UPDATE_ORDERID_BY_ROUTEID =
            "UPDATE route SET orderid=? WHERE routeid=?";
    private static final String INSERT =
            "INSERT INTO route(startmarker,finalmarker,length) VALUES(?,?,?)";

    public Boolean updateOrderIDbyRouteID(Integer routeID,Integer orderID) {
        return executeQuery(UPDATE_ORDERID_BY_ROUTEID,
                List.of(
                        String.valueOf(orderID),
                        String.valueOf(routeID)
                ));
    }

    public Integer insert(Route route) {
        return insert(INSERT, route);
    }

    @Override
    protected Route buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildRoute(resultSet);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Route entity) throws SQLException {
        Connection connection = connectionPool.getConnection();
        preparedStatement.setArray(1, connection.createArrayOf("DOUBLE",entity.getStartMarker()));
        preparedStatement.setArray(2, connection.createArrayOf("DOUBLE",entity.getFinalMarker()));
        preparedStatement.setInt(3, entity.getLength());
        connectionPool.returnConnection(connection);
    }

}
