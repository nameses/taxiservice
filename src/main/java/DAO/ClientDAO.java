package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.User.Client;
import entity.User.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAO extends DAO<Client> {
    private static final String INSERT =
            "INSERT INTO client(userid,\"bonusPoints\") VALUES(?,?)";
    private static final String SELECT_BY_USER_ID =
            "SELECT * FROM client join \"user\" on client.userid=\"user\".userid WHERE \"user\".userid=? ";
    @Override
    protected Client buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildClient(resultSet);
    }
    public Client getByUserID(Integer userID){
        return selectEntityByID(SELECT_BY_USER_ID, userID);
    }

    public Boolean insert(Client client) {
        return this.executeQuery(INSERT,
                List.of(
                        String.valueOf(client.getUserID()),
                        String.valueOf(client.getBonusPoints())
                ));
    }

    @Override
    protected void setStatement(PreparedStatement preparedStatement, Client entity) throws SQLException {
        return;
    }
}
