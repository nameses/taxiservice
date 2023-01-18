package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import exceptions.DAOException;
import models.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends DAO<User> {
    private final static String INSERT =
            "INSERT INTO \"user\"(username,password,fullname,phone,email,role) " +
                    "VALUES(?,?,?,?,?,?::userrole)";
    private final static String SELECT_LOGIN =
            "SELECT * FROM \"user\" WHERE username=? AND password=?";
    private final static String SELECT_USERNAME_BY_ID =
            "SELECT username FROM user WHERE userid=?";

    public Boolean insert(User user) throws DAOException {
        return this.executeQuery(INSERT,
                List.of(
                        user.getUsername(),
                        user.getPassword(),
                        user.getFullname(),
                        user.getPhone(),
                        user.getEmail(),
                        user.getRole().toString()
                ));
    }

    public User login(String username, String password) {
        return getEntity(SELECT_LOGIN, List.of(username, password));
    }

    protected void setStatement(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getUsername());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getFullname());
        preparedStatement.setString(4, entity.getPhone());
        preparedStatement.setString(5, entity.getEmail());
        preparedStatement.setString(6, entity.getRole().toString());
    }
    @Override
    protected User buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildUser(resultSet);
    }
}
