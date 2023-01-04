package service;

import DAO.UserDAO;
import entity.User.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    public User login(String username, String password){
        try{
            return userDAO.login(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean registerUser(User user){
        try{
            return userDAO.insertUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
