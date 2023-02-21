package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import exceptions.DAOException;
import models.DTO.ClientDTO;
import models.converters.ClientConverter;
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
    protected Client buildEntity(ResultSet resultSet) throws DAOException {
        return EntityBuilder.buildClient(resultSet);
    }

    public ClientDTO getByUserID(Integer userID) throws DAOException{
        return ClientConverter.toDTO(select(SELECT_BY_USER_ID, userID));
    }

    public ClientDTO insert(Client client) throws DAOException {
        Integer id = this.insert(INSERT, client.getUserID());
        return new ClientDTO(id!=null && id>0);
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Client entity) throws SQLException {
        return;
    }
}
