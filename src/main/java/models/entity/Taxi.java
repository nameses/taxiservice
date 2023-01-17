package models.entity;

import models.entity.enums.CarCategory;

import java.util.Objects;

public class Taxi {
    private Integer taxiID;
    private Integer driverID;
    private Integer capacity;
    private CarCategory category;
    private Integer fare;
    private String licensePlate;

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

    public Integer getDriverID() {
        return this.driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }
}
