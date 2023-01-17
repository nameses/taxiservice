package models.DTO;

import models.entity.enums.CarCategory;

public class TaxiDTO {
    private Integer taxiID;
    private Integer driverID;
    private Integer capacity;
    private CarCategory category;
    private Integer fare;
    private String licensePlate;
    private Boolean success;
    private String message;

    public TaxiDTO() {
    }

    public TaxiDTO(Boolean success) {
        this.success = success;
    }

    public TaxiDTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public TaxiDTO(Integer taxiID, Integer driverID, Integer capacity, CarCategory category, Integer fare, String licensePlate) {
        this.taxiID = taxiID;
        this.driverID = driverID;
        this.capacity = capacity;
        this.category = category;
        this.fare = fare;
        this.licensePlate = licensePlate;
    }

    public TaxiDTO(Integer driverID, Integer capacity,  Integer fare, String licensePlate, CarCategory category) {
        this.driverID = driverID;
        this.capacity = capacity;
        this.category = category;
        this.fare = fare;
        this.licensePlate = licensePlate;
    }
    public TaxiDTO(Integer capacity,  Integer fare, String licensePlate, CarCategory category) {
        this.capacity = capacity;
        this.category = category;
        this.fare = fare;
        this.licensePlate = licensePlate;
    }

    public Integer getTaxiID() {
        return taxiID;
    }

    public void setTaxiID(Integer taxiID) {
        this.taxiID = taxiID;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public CarCategory getCategory() {
        return category;
    }

    public void setCategory(CarCategory category) {
        this.category = category;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }
}
