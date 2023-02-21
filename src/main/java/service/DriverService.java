package service;

import DAO.DriverDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.converters.DriverConverter;
import models.entity.Driver;
import models.entity.enums.DriverStatus;
import models.view.DriverView;

public class DriverService {
    private final DriverDAO driverDAO = new DriverDAO();

    public DriverDTO selectByID(DriverDTO driverDTO) throws ServiceException {
        try{
            return driverDAO.selectByDriverID(driverDTO.getDriverID());
        }catch(DAOException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public Boolean updateDriverStatus(Integer userID, DriverStatus driverStatus) throws ServiceException {
        try {
            return driverDAO.updateDriverStatus(userID, driverStatus);
        } catch(DAOException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public DriverDTO register(DriverDTO driverDTO) throws ServiceException {
        try {
            return driverDAO.insert(driverDTO.getUserID());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
