package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.User.Driver;

import java.sql.ResultSet;
import java.util.List;

public class DriverDAO extends DAO<Driver> {
    private static final String INSERT =
            "INSERT INTO driver(userid,taxiid,status) VALUES(?,?,?)";
    private static final String SELECT_BY_USER_ID =
            "SELECT * FROM driver join \"User\" on driver.userid=\"User\".userid " +
                    "join taxi on driver.taxiid=taxi.taxiid " +
                    "WHERE driver.userid=? ";

    @Override
    protected Driver buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildDriver(resultSet);
    }

    public Driver getByUserID(Integer userID) {
        return selectEntityByID(SELECT_BY_USER_ID, userID);
    }

    public Boolean insert(Driver driver) {
        return this.executeQuery(INSERT,
                List.of(
                        String.valueOf(driver.getUserID()),
                        String.valueOf(driver.getTaxiID()),
                        String.valueOf(driver.getDriverStatus())
                ));
    }
}
