package DAO;

import entity.UserAccount;

import javax.xml.registry.infomodel.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<UserAccount> {
    private final static String INSERT =
            "INSERT INTO UserAccount(firstname,lastname,username,password,phone,email,role) " +
                    "VALUES(?,?,?,?,?,?,?)";
    private final static String SELECT_LOGIN =
            "SELECT * FROM UserAccount WHERE username=? AND password=?";
    private final static String SELECT_USERNAME_BY_ID =
            "SELECT username FROM UserAccount WHERE id=?";

    public Boolean insertUser(UserAccount user) {
        return insert(INSERT, user);
    }

    public String selectUsernameById(Integer id) {
        UserAccount user = selectEntityByID(SELECT_USERNAME_BY_ID, id);
        return user.getFirstname()+user.getLastname();
    }

    public UserAccount login(String username, String password) {
        List<String> params = List.of(username, password);
        return getEntity(SELECT_LOGIN, params);
    }

    @Override
    protected List<String> getParameters(UserAccount user) {
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String username = user.getUsername();
        String password = user.getPassword();
        String phone = user.getPhone();
        String email = user.getEmail();
        String role = user.getRole();
        return List.of(firstname, lastname, username, password, phone, email, role);
    }

    @Override
    protected UserAccount buildEntity(ResultSet resultSet) {
        try {
            UserAccount user = new UserAccount();
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(resultSet.getString("role"));
            user.setBonusPoints(resultSet.getInt("bonus_points"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
