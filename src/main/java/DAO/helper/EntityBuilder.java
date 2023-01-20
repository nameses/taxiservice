package DAO.helper;

import exceptions.DAOException;
import models.entity.Order;
import models.entity.Route;
import models.entity.Taxi;
import models.entity.Client;
import models.entity.Driver;
import models.entity.User;
import models.entity.enums.CarCategory;
import models.entity.enums.OrderStatus;
import models.entity.enums.DriverStatus;
import models.entity.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityBuilder {
    public static Client buildClient(ResultSet resultSet) throws DAOException {
        try {
            Client client = new Client();
            client.setClientID(resultSet.getInt("clientid"));
            client.setUserID(resultSet.getInt("userid"));
            client.setBonusPoints(resultSet.getInt("bonusPoints"));
            return client;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }


    public static Driver buildDriver(ResultSet resultSet) throws DAOException {
        try {
            Driver driver = new Driver();
            driver.setDriverID(resultSet.getInt("driverid"));
            driver.setUserID(resultSet.getInt("userid"));
            driver.setDriverStatus(DriverStatus.valueOf(resultSet.getString("driverStatus")));
            return driver;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }


    public static Order buildOrder(ResultSet resultSet) throws DAOException {
        try {
            Order order = new Order();
            order.setOrderID(resultSet.getInt("orderid"));
            order.setClientID(resultSet.getInt("clientid"));
            order.setOrderOpened(resultSet.getTimestamp("orderopened"));
            order.setCarCapacity(resultSet.getInt("carCapacity"));
            order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("status")));
            order.setCarCategory(CarCategory.valueOf(resultSet.getString("carcategory")));
            if (order.getOrderStatus() != OrderStatus.processing) {
                order.setDriverID(resultSet.getInt("driverid"));
                order.setOrderAccepted(resultSet.getTimestamp("orderaccepted"));
                order.setCost(resultSet.getInt("cost"));
            }
            return order;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public static Route buildRoute(ResultSet resultSet) throws DAOException {
        try {
            Route route = new Route();
            route.setRouteID(resultSet.getInt("routeid"));
            route.setStartMarker((Float[]) resultSet.getArray("startmarker").getArray());
            route.setFinalMarker((Float[]) resultSet.getArray("finalmarker").getArray());
            route.setLength(resultSet.getInt("length"));
            route.setOrderID(resultSet.getInt("orderid"));
            return route;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }


    public static Taxi buildTaxi(ResultSet resultSet) throws DAOException {
        try {
            Taxi taxi = new Taxi();
            taxi.setTaxiID(resultSet.getInt("taxiid"));
            taxi.setDriverID(resultSet.getInt("driverid"));
            taxi.setCapacity(resultSet.getInt("capacity"));
            taxi.setCategory(CarCategory.valueOf(resultSet.getString("carcategory")));
            taxi.setFare(resultSet.getInt("fare"));
            taxi.setLicensePlate(resultSet.getString("licensePlate"));
            return taxi;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public static User buildUser(ResultSet resultSet) throws DAOException {
        try {
            User user = new User();
            user.setUserID(resultSet.getInt("userid"));
            user.setUsername(resultSet.getString("username"));
            user.setFullname(resultSet.getString("fullname"));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(UserRole.valueOf(resultSet.getString("role")));
            return user;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}
