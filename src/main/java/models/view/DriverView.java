package models.view;

import models.entity.enums.DriverStatus;

public class DriverView {
    private Integer driverID;
    private Integer userID;
    private DriverStatus driverStatus;

    public DriverView() {
    }

    public DriverView(Integer driverID, Integer userID, DriverStatus driverStatus) {
        this.driverID = driverID;
        this.userID = userID;
        this.driverStatus = driverStatus;
    }

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }
}
