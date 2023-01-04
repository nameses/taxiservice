package DAO;

import entity.User.User;
import entity.enums.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends DAO<User> {
    private final static String INSERT =
            "INSERT INTO UserAccount(firstname,lastname,username,password,phone,email,role,bonus_points) " +
                    "VALUES(?,?,?,?,?,?,?)";
    private final static String SELECT_LOGIN =
            "SELECT * FROM UserAccount WHERE username=? AND password=?";
    private final static String SELECT_USERNAME_BY_ID =
            "SELECT username FROM UserAccount WHERE id=?";

    public Boolean insertUser(User user) {
        return insert(INSERT, user);
    }

    public User login(String username, String password) {
        List<String> params = List.of(username, password);
        return getEntity(SELECT_LOGIN, params);
    }

    @Override
    protected List<String> getParameters(User user) {
        String fullname = user.getFullname();
        String username = user.getUsername();
        String password = user.getPassword();
        String phone = user.getPhone();
        String email = user.getEmail();
        UserRole role = user.getRole();
        return List.of(fullname, username, password, phone, email, role.toString());
    }

    @Override
    protected User buildEntity(ResultSet resultSet) {
        try {
            User user = new User();
            user.setFullname(resultSet.getString("fullname"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(UserRole.valueOf(resultSet.getString("role")));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
