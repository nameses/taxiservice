package entity;

import java.sql.Timestamp;
import java.util.Objects;

public class TaxiOrder {
    private Integer orderID;
    private Integer userID;
    private Integer taxiID;
    private Timestamp orderOpened;
    private Timestamp orderAccepted;
    private Integer cost;
    private String status;
    private Taxi taxi;
    private UserAccount user;

    public Taxi getTaxi() {
        if (taxi == null) taxi = new Taxi();
        return taxi;
    }

    public UserAccount getUser() {
        if (user == null) user = new UserAccount();
        return user;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getTaxiID() {
        return taxiID;
    }

    public void setTaxiID(Integer taxiID) {
        this.taxiID = taxiID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiOrder taxiOrder = (TaxiOrder) o;
        return Objects.equals(orderID, taxiOrder.orderID) && Objects.equals(userID, taxiOrder.userID) && Objects.equals(taxiID, taxiOrder.taxiID) && Objects.equals(orderOpened, taxiOrder.orderOpened) && Objects.equals(orderAccepted, taxiOrder.orderAccepted) && Objects.equals(cost, taxiOrder.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, userID, taxiID, orderOpened, orderAccepted, cost);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
