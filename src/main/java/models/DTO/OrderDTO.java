package models.DTO;

import models.entity.enums.CarCategory;
import models.entity.enums.OrderStatus;

import java.sql.Timestamp;

public class OrderDTO {
    private Integer orderID;
    private Integer driverID;
    private Integer clientID;
    private Timestamp orderOpened;
    private Timestamp orderAccepted;
    private Integer cost;
    private Integer carCapacity;
    private CarCategory carCategory;
    private OrderStatus orderStatus;
    private String message;
    private Boolean success;

    public OrderDTO(Integer orderID, Boolean success, String message) {
        this.orderID = orderID;
        this.message = message;
        this.success = success;
    }
    public OrderDTO(Boolean status, String message) {
        this.message = message;
        this.success = status;
    }

    public OrderDTO(Integer orderID,Boolean success) {
        this.orderID = orderID;
        this.success = success;
    }
    public OrderDTO(Boolean success) {
        this.success = success;
    }

    public OrderDTO() {
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
