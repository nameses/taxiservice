package DAO.helper;

import entity.Order;
import entity.Route;
import entity.Taxi;
import entity.User.Client;
import entity.User.Driver;
import entity.User.User;
import entity.enums.CarCategory;
import entity.enums.OrderStatus;
import entity.enums.DriverStatus;
import entity.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityBuilder {
    public static Client buildClient(ResultSet resultSet) {
        try {
            Client client = new Client();
            client.setClientID(resultSet.getInt("clientid"));
            client.setUserID(resultSet.getInt("userid"));
            client.setUser(buildUser(resultSet));
            client.setBonusPoints(resultSet.getInt("bonusPoints"));
            return client;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Driver buildDriver(ResultSet resultSet) {
        try {
            Driver driver = new Driver();
            driver.setDriverID(resultSet.getInt("driverid"));
            driver.setUserID(resultSet.getInt("userid"));
            driver.setUser(buildUser(resultSet));
            driver.setDriverStatus(DriverStatus.valueOf(resultSet.getString("driverStatus")));
            try {
            driver.setTaxiID(resultSet.getInt("taxiid"));
            driver.setTaxi(buildTaxi(resultSet));
            }catch(Exception ignored){
                driver.setTaxiID(null);
                driver.setTaxi(null);
            }
            return driver;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Order buildOrder(ResultSet resultSet) {
        try {
            Order order = new Order();
            order.setOrderID(resultSet.getInt("orderid"));
            order.setClientID(resultSet.getInt("clientid"));
            order.setDriverID(resultSet.getInt("driverid"));
            order.setDriver(buildDriver(resultSet));
            order.setClient(buildClient(resultSet));
            order.setOrderOpened(resultSet.getTimestamp("orderopened"));
            order.setOrderAccepted(resultSet.getTimestamp("orderaccepted"));
            order.setCost(resultSet.getInt("cost"));
            order.setCarCapacity(resultSet.getInt("carCapacity"));
            order.setCarCategory(CarCategory.valueOf(resultSet.getString("username")));
            order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("orderStatus")));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Route buildRoute(ResultSet resultSet) {
        try {
            Route route = new Route();
            route.setRouteID(resultSet.getInt("routeid"));
            route.setStartMarker((Double[])resultSet.getArray("startmarker").getArray());
            route.setFinalMarker((Double[])resultSet.getArray("finalmarker").getArray());
            return route;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Taxi buildTaxi(ResultSet resultSet) {
        try {
            Taxi taxi = new Taxi();
            taxi.setTaxiID(resultSet.getInt("taxiid"));
            taxi.setCapacity(resultSet.getInt("capacity"));
            taxi.setCategory(CarCategory.valueOf(resultSet.getString("category")));
            taxi.setFare(resultSet.getInt("fare"));
            taxi.setLicensePlate(resultSet.getString("licensePlate"));
            return taxi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User buildUser(ResultSet resultSet) {
        try {
            User user = new User();
            user.setUserID(resultSet.getInt("userid"));
            user.setFullname(resultSet.getString("fullname"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(UserRole.valueOf(resultSet.getString("role")));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
