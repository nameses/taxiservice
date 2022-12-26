package entity;

import java.util.Objects;

public class Taxi {
    private Integer taxiID;
    private String status;
    private String capacity;
    private String category;
    private Integer fare;
    private String licensePlate;
    private String driverName;

    public Integer getTaxiID() {
        return taxiID;
    }

    public void setTaxiID(Integer taxiID) {
        this.taxiID = taxiID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxi taxi = (Taxi) o;
        return Objects.equals(taxiID, taxi.taxiID) && Objects.equals(status, taxi.status) && Objects.equals(capacity, taxi.capacity) && Objects.equals(category, taxi.category) && Objects.equals(fare, taxi.fare) && Objects.equals(licensePlate, taxi.licensePlate) && Objects.equals(driverName, taxi.driverName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxiID, status, capacity, category, fare, licensePlate, driverName);
    }
}
