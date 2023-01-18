package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import exceptions.DAOException;
import models.DTO.ClientDTO;
import models.entity.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAO extends DAO<Client> {
    private static final String INSERT =
            "INSERT INTO client(userid) VALUES(?)";
    private static final String SELECT_BY_USER_ID =
            "SELECT * FROM client join \"user\" on client.userid=\"user\".userid WHERE \"user\".userid=? ";

    @Override
    protected Client buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildClient(resultSet);
    }

    public Client getByUserID(Integer userID) {
        return selectByID(SELECT_BY_USER_ID, userID);
    }

    public ClientDTO insert(Client client) throws DAOException {
        Integer id = this.insert(INSERT, client.getUserID());
        return new ClientDTO(id!=null);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Client entity) throws SQLException {
        return;
    }
}
