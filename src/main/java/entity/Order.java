package entity;

import entity.User.Driver;
import entity.User.User;
import entity.enums.CarCategory;
import entity.enums.ClientStatus;
import java.sql.Timestamp;
import java.util.Objects;

public class Order {
    private Integer orderID;
    private User user;
    private Driver driver;
    private Timestamp orderOpened;
    private Timestamp orderAccepted;
    private Integer cost;
    private Integer carCapacity;
    private CarCategory carCategory;
    private ClientStatus status;


    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID) && Objects.equals(user, order.user) && Objects.equals(driver, order.driver) && Objects.equals(orderOpened, order.orderOpened) && Objects.equals(orderAccepted, order.orderAccepted) && Objects.equals(cost, order.cost) && Objects.equals(carCapacity, order.carCapacity) && carCategory == order.carCategory && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, user, driver, orderOpened, orderAccepted, cost, carCapacity, carCategory, status);
    }
}
