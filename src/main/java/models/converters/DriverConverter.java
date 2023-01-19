package models.converters;

import models.DTO.DriverDTO;
import models.entity.Driver;
import models.view.DriverView;

public class DriverConverter {
    public static Driver toEntity(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setDriverID(driverDTO.getDriverID());
        driver.setUserID(driverDTO.getUserID());
        driver.setDriverStatus(driverDTO.getDriverStatus());
        return driver;
    }

    public static DriverDTO toDTO(DriverView driverView) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setDriverID(driverView.getDriverID());
        driverDTO.setUserID(driverView.getUserID());
        driverDTO.setDriverStatus(driverView.getDriverStatus());
        return driverDTO;
    }
    public static DriverDTO toDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setDriverID(driver.getDriverID());
        driverDTO.setUserID(driver.getUserID());
        driverDTO.setDriverStatus(driver.getDriverStatus());
        return driverDTO;
    }

    public static DriverView toView(DriverDTO driverDTO) {
        DriverView driverView = new DriverView();
        driverView.setDriverID(driverDTO.getDriverID());
        driverView.setUserID(driverDTO.getUserID());
        driverView.setDriverStatus(driverDTO.getDriverStatus());
        return driverView;
    }
}