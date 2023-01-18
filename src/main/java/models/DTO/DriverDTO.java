package models.DTO;

import models.entity.enums.DriverStatus;

public class DriverDTO {
    private Integer driverID;
    private Integer userID;
    private Integer taxiID;
    private DriverStatus driverStatus;
    private Boolean success;
    private String message;

    public DriverDTO(Boolean success) {
        this.success = success;
    }

    public DriverDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public DriverDTO() {
    }

    public DriverDTO(Integer driverID, Integer userID, Integer taxiID, DriverStatus driverStatus) {
        this.driverID = driverID;
        this.userID = userID;
        this.taxiID = taxiID;
        this.driverStatus = driverStatus;
    }

    public DriverDTO(Integer userID, Integer taxiID, DriverStatus driverStatus) {
        this.userID = userID;
        this.taxiID = taxiID;
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

    public Integer getTaxiID() {
        return taxiID;
    }

    public void setTaxiID(Integer taxiID) {
        this.taxiID = taxiID;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
