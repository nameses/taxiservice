package models.view;

import models.entity.enums.CarCategory;

public class TaxiView {
    private Integer capacity;
    private CarCategory category;
    private Integer fare;
    private String licensePlate;

    public TaxiView(Integer capacity, CarCategory category, Integer fare, String licensePlate) {
        this.capacity = capacity;
        this.category = category;
        this.fare = fare;
        this.licensePlate = licensePlate;
    }

    public TaxiView() {
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
}
