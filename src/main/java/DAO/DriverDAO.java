package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.User.Driver;

import java.sql.ResultSet;

public class DriverDAO extends DAO<Driver> {

    @Override
    protected Driver buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildDriver(resultSet);
    }
}
