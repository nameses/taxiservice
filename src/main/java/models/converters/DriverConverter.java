package models.converters;

import models.DTO.DriverDTO;
import models.entity.Driver;

public class DriverConverter {
    public static Driver toEntity(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setDriverID(driverDTO.getDriverID());
        driver.setUserID(driverDTO.getUserID());
        driver.setDriverStatus(driverDTO.getDriverStatus());
        return driver;
    }

    public static DriverDTO toDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setDriverID(driver.getDriverID());
        driverDTO.setUserID(driver.getUserID());
        driverDTO.setDriverStatus(driver.getDriverStatus());
        return driverDTO;
    }
}
