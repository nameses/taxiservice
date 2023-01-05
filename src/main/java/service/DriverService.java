package service;

import DAO.DriverDAO;
import DAO.OrderDAO;
import entity.User.Driver;
import entity.User.User;

public class DriverService {
    private final DriverDAO driverDAO = new DriverDAO();

    public Boolean register(Driver driver){
        try{
            return driverDAO.insert(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
