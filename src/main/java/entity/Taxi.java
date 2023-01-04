package entity;

import entity.enums.CarCategory;

import java.util.Objects;

public class Taxi {
    private Integer taxiID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxi taxi = (Taxi) o;
        return Objects.equals(taxiID, taxi.taxiID) && Objects.equals(capacity, taxi.capacity) && category == taxi.category && Objects.equals(fare, taxi.fare) && Objects.equals(licensePlate, taxi.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxiID, capacity, category, fare, licensePlate);
    }
}
