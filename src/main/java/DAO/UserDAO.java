package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import exceptions.DAOException;
import models.DTO.UserDTO;
import models.converters.UserConverter;
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
            "SELECT userid,username,fullname,phone,email,role FROM \"user\" WHERE username=? AND password=?";
    private final static String SELECT_USERNAME_BY_ID =
            "SELECT username FROM user WHERE userid=?";
    private final static String SELECT_BY_USERNAME =
            "SELECT * FROM \"user\" WHERE username=?";
    private final static String SELECT_BY_FULLNAME =
            "SELECT * FROM \"user\" WHERE fullname=?";
    private final static String SELECT_BY_PHONE =
            "SELECT * FROM \"user\" WHERE phone=?";
    private final static String SELECT_BY_EMAIL =
            "SELECT * FROM \"user\" WHERE email=?";


    public UserDTO validateData(User user) throws DAOException {
        UserDTO userDTO = new UserDTO();
        if (this.select(SELECT_BY_USERNAME, user.getUsername()) != null) {
            userDTO.addMessages("Username already exists");
        }
        if (this.select(SELECT_BY_FULLNAME, user.getFullname()) != null) {
            userDTO.addMessages("Fullname already exists");
        }
        if (this.select(SELECT_BY_PHONE, user.getPhone()) != null) {
            userDTO.addMessages("Phone already exists");
        }
        if (this.select(SELECT_BY_EMAIL, user.getEmail()) != null) {
            userDTO.addMessages("Email already exists");
        }
        if (userDTO.getMessages() != null) {
            userDTO.setSuccess(false);
            return userDTO;
        } else return new UserDTO(true);
    }

    public UserDTO insert(User user) throws DAOException {
        Integer id = this.insert(INSERT, user);
        if (id != null && id>0) {
            UserDTO userDTO = UserConverter.toDTO(user);
            userDTO.setSuccess(true);
            userDTO.setUserID(id);
            return userDTO;
        } else return new UserDTO(false);
    }

    public UserDTO login(User user) throws DAOException {
        User response = select(SELECT_LOGIN, user.getUsername(), user.getPassword());
        if (response == null) return new UserDTO(false);
        UserDTO userDTO = UserConverter.toDTO(response);
        userDTO.setSuccess(true);
        return userDTO;
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
    protected User buildEntity(ResultSet resultSet) throws DAOException {
        return EntityBuilder.buildUser(resultSet);
    }
}
