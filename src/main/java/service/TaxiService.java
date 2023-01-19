package service;

import DAO.TaxiDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.TaxiDTO;
import models.converters.TaxiConverter;
import models.entity.Driver;

public class TaxiService {
    private final TaxiDAO taxiDAO = new TaxiDAO();

    public TaxiDTO findByDriver(DriverDTO driverDTO) throws ServiceException {
        try {
            return taxiDAO.selectByDriver(driverDTO.getDriverID());
        }catch (DAOException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
    public TaxiDTO validate(TaxiDTO taxiDTO) {
        try {
            TaxiDTO responseTaxi = taxiDAO.selectByDriver(taxiDTO.getDriverID());
            if(!responseTaxi.getSuccess()) return new TaxiDTO(true);
            else return new TaxiDTO(false,"You already have a taxi");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public TaxiDTO newTaxi(TaxiDTO taxiDTO) {
        try {
            return taxiDAO.insert(TaxiConverter.toEntity(taxiDTO));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TaxiDTO(false,"Unknown error. Try again later.");
    }
}
