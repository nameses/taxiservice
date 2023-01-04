package entity.User;

import entity.Taxi;

import java.util.Objects;

public class Driver{
    private Integer driverID;
    private Taxi taxi;
    private User user;
    private Integer status;

    public Integer getDriverID() {
        return driverID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driverID, driver.driverID) && Objects.equals(taxi, driver.taxi) && Objects.equals(user, driver.user) && Objects.equals(status, driver.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverID, taxi, user, status);
    }
}
