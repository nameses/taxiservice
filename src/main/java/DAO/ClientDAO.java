package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.User.Client;

import java.sql.ResultSet;

public class ClientDAO extends DAO<Client> {

    @Override
    protected Client buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildClient(resultSet);
    }
}
