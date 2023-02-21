package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import exceptions.DAOException;
import models.DTO.TaxiDTO;
import models.converters.TaxiConverter;
import models.entity.Taxi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaxiDAO extends DAO<Taxi> {
    private static final String SELECT_BY_DRIVER =
            "SELECT * FROM taxi WHERE driverid=?";
    private static final String INSERT =
            "INSERT INTO taxi(capacity,fare,\"licenseplate\",carcategory,driverid) " +
                    "VALUES(?,?,?,?::carcategory,?)";

    public TaxiDTO selectByDriverID(Integer driverID) throws DAOException {
        Taxi taxi = select(SELECT_BY_DRIVER, driverID);
        if (taxi == null)
            return new TaxiDTO(false, "You don't have a taxi");
        else {
            TaxiDTO taxiDTO = TaxiConverter.toDTO(taxi);
            taxiDTO.setSuccess(true);
            return taxiDTO;
        }
    }

    public TaxiDTO insert(Taxi taxi) throws DAOException {
        Integer id = this.insert(INSERT, taxi);
        TaxiDTO response;
        if (id != null) {
            response = new TaxiDTO(true);
            response.setTaxiID(id);
        } else {
            response = new TaxiDTO(false, "Unknown error. Try again later.");
        }
        return response;
    }

    @Override
    protected Taxi buildEntity(ResultSet resultSet) throws DAOException {
        return EntityBuilder.buildTaxi(resultSet);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Taxi entity) throws SQLException {
        preparedStatement.setInt(1, entity.getCapacity());
        preparedStatement.setInt(2, entity.getFare());
        preparedStatement.setString(3, entity.getLicensePlate());
        preparedStatement.setString(4, entity.getCategory().toString());
        preparedStatement.setInt(5, entity.getDriverID());
    }


}
