package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import models.DTO.DriverDTO;
import models.DTO.TaxiDTO;
import models.entity.User.Driver;
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

    public Driver getByUserID(Integer userID) {
        return selectEntityByID(SELECT_BY_USER_ID, userID);
    }

    public Boolean insert(Driver driver) {
        return this.executeQuery(INSERT,
                List.of(
                        String.valueOf(driver.getUserID())
                ));
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
