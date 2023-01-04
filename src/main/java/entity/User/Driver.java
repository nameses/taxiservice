package entity.User;

import entity.Taxi;
import entity.enums.DriverStatus;

import java.util.Objects;

public class Driver{
    private Integer driverID;
    private Integer userID;
    private Integer taxiID;
    private Taxi taxi;

    private User user;

    private DriverStatus driverStatus;

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driverID, driver.driverID) && Objects.equals(userID, driver.userID) && Objects.equals(taxiID, driver.taxiID) && Objects.equals(taxi, driver.taxi) && Objects.equals(user, driver.user) && driverStatus == driver.driverStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverID, userID, taxiID, taxi, user, driverStatus);
    }
}
