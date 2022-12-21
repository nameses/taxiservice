package entity;

import java.util.Objects;

public class Taxi {
    private Integer taxiID;
    private String status;
    private String capacity;
    private String category;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxi taxi = (Taxi) o;
        return Objects.equals(taxiID, taxi.taxiID) && Objects.equals(status, taxi.status) && Objects.equals(capacity, taxi.capacity) && Objects.equals(category, taxi.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxiID, status, capacity, category);
    }
}
