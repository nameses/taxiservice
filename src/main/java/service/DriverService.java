package service;

import DAO.DriverDAO;
import models.DTO.DriverDTO;
import models.DTO.TaxiDTO;
import models.entity.User.Driver;
import models.entity.enums.DriverStatus;

public class DriverService {
    private final DriverDAO driverDAO = new DriverDAO();

    public Boolean updateDriverStatus(Integer userID, DriverStatus driverStatus) {
        return driverDAO.updateDriverStatus(userID, driverStatus);
    }

    public Boolean register(Driver driver) {
        try {
            return driverDAO.insert(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
