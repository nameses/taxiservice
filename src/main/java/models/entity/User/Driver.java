package models.entity.User;

import models.entity.Taxi;
import models.entity.enums.DriverStatus;

import java.util.Objects;

public class Driver {
    private Integer driverID;
    private Integer userID;
    private DriverStatus driverStatus;

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
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
}
