package models.DTO;

import models.entity.enums.DriverStatus;

public class DriverDTO {
    private Integer driverID;
    private Integer userID;
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

    public DriverDTO(Boolean success, Integer driverID, Integer userID) {
        this.success = success;
        this.driverID = driverID;
        this.userID = userID;
    }

    public DriverDTO(Integer driverID) {
        this.driverID=driverID;
    }
    public DriverDTO(Integer driverID, Integer userID) {
        this.driverID = driverID;
        this.userID = userID;
    }

    public DriverDTO(Integer driverID, Integer userID, DriverStatus driverStatus) {
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
