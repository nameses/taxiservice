package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import exceptions.DAOException;
import models.DTO.DriverDTO;
import models.converters.DriverConverter;
import models.entity.Driver;
import models.entity.enums.DriverStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DriverDAO extends DAO<Driver> {
    private static final String INSERT =
            "INSERT INTO driver(userid) VALUES(?)";
    private static final String SELECT_BY_DRIVER_ID =
            "SELECT * FROM driver WHERE driver.driverid=?";
    private static final String SELECT_BY_USER_ID =
            "SELECT * FROM driver WHERE driver.userid=?";
    private static final String UPDATE_ENUM_TO_STATUS_TO_ALL =
            "UPDATE driver SET \"driverStatus\"=?::driverstatus";
    private static final String UPDATE_ENUM_TO_STATUS =
            "UPDATE driver SET \"driverStatus\"=?::driverstatus WHERE userid=?";

    public Boolean inactivateAllDrivers() throws DAOException {
        return executeQuery(UPDATE_ENUM_TO_STATUS_TO_ALL, DriverStatus.inactive);
    }
    public Boolean updateDriverStatus(Integer id, DriverStatus driverStatus) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement =
                    this.prepareStatement(connection,
                            UPDATE_ENUM_TO_STATUS,
                            List.of(driverStatus.toString(), String.valueOf(id)));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    public DriverDTO selectByDriverID(Integer driverID) throws DAOException {
        return DriverConverter.toDTO(select(SELECT_BY_DRIVER_ID, driverID));
    }

    public DriverDTO logout(Integer userID) throws DAOException {
        return new DriverDTO(updateDriverStatus(userID, DriverStatus.inactive));
    }

    public DriverDTO login(Integer userID) throws DAOException {
        updateDriverStatus(userID, DriverStatus.active);
        return DriverConverter.toDTO(select(SELECT_BY_USER_ID, userID));
    }

    public DriverDTO insert(Integer userID) throws DAOException {
        Integer id = this.insert(INSERT, userID);
        return new DriverDTO(id != null && id > 0);
    }

    @Override
    protected Driver buildEntity(ResultSet resultSet) throws DAOException {
        return EntityBuilder.buildDriver(resultSet);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Driver entity) throws SQLException {
        return;
    }
}
