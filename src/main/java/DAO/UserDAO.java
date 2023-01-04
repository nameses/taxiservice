package DAO;

import DAO.helper.DAO;
import DAO.helper.EntityBuilder;
import entity.User.User;

import java.sql.ResultSet;
import java.util.List;

public class UserDAO extends DAO<User> {
    private final static String INSERT =
            "INSERT INTO User(username,password,fullname,phone,email,role) " +
                    "VALUES(?,?,?,?,?,?,?)";
    private final static String SELECT_LOGIN =
            "SELECT * FROM User WHERE username=? AND password=?";
    private final static String SELECT_USERNAME_BY_ID =
            "SELECT username FROM User WHERE userid=?";

    public Boolean insertUser(User user) {
        return this.executeQuery(INSERT,
                List.of(
                        user.getUsername(),
                        user.getPassword(),
                        user.getFullname(),
                        user.getPhone(),
                        user.getEmail(),
                        String.valueOf(user.getRole())
                ));
    }

    public User login(String username, String password) {
        List<String> params = List.of(username, password);
        return getEntity(SELECT_LOGIN, params);
    }


    @Override
    protected User buildEntity(ResultSet resultSet) {
        return EntityBuilder.buildUser(resultSet);
    }
}
