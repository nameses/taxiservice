package entity;

import entity.User.Client;
import entity.User.Driver;
import entity.User.User;
import entity.enums.CarCategory;
import entity.enums.ClientStatus;
import java.sql.Timestamp;
import java.util.Objects;

public class Order {
    private Integer orderID;
    private Integer driverID;
    private Integer clientID;
    private Integer routeID;
    private Route route;
    private Client client;

    private Driver driver;

    private Timestamp orderOpened;
    private Timestamp orderAccepted;
    private Integer cost;
    private Integer carCapacity;
    private CarCategory carCategory;
    private ClientStatus clientStatus;


    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Timestamp getOrderOpened() {
        return orderOpened;
    }

    public void setOrderOpened(Timestamp orderOpened) {
        this.orderOpened = orderOpened;
    }

    public Timestamp getOrderAccepted() {
        return orderAccepted;
    }

    public void setOrderAccepted(Timestamp orderAccepted) {
        this.orderAccepted = orderAccepted;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(Integer carCapacity) {
        this.carCapacity = carCapacity;
    }

    public CarCategory getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(CarCategory carCategory) {
        this.carCategory = carCategory;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus status) {
        this.clientStatus = clientStatus;
    }

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getRouteID() {
        return routeID;
    }

    public void setRouteID(Integer routeID) {
        this.routeID = routeID;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID) && Objects.equals(driverID, order.driverID) && Objects.equals(clientID, order.clientID) && Objects.equals(routeID, order.routeID) && Objects.equals(route, order.route) && Objects.equals(client, order.client) && Objects.equals(driver, order.driver) && Objects.equals(orderOpened, order.orderOpened) && Objects.equals(orderAccepted, order.orderAccepted) && Objects.equals(cost, order.cost) && Objects.equals(carCapacity, order.carCapacity) && carCategory == order.carCategory && clientStatus == order.clientStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, driverID, clientID, routeID, route, client, driver, orderOpened, orderAccepted, cost, carCapacity, carCategory, clientStatus);
    }
}
