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
    private static final String SELECT_BY_USER_ID =
            "SELECT * FROM driver WHERE driver.userid=?";
    private static final String UPDATE_ENUM_TO_STATUS =
            "UPDATE driver SET \"driverStatus\"=?::driverstatus WHERE userid=?";

    public Boolean updateDriverStatus(Integer id, DriverStatus driverStatus) {
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

    public DriverDTO getByUserID(Integer userID) throws DAOException {
        return DriverConverter.toDTO(selectByID(SELECT_BY_USER_ID, userID));
    }

    public DriverDTO insert(Integer userID) throws DAOException {
        Integer id = this.insert(INSERT, userID);
        return new DriverDTO(id!=null && id>0);
    }

    @Override
    protected Driver buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildDriver(resultSet);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Driver entity) throws SQLException {
        return;
    }
}
