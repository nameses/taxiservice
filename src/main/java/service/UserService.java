package service;

import DAO.UserDAO;
import entity.UserAccount;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public UserAccount login(String username, String password){
        try{
            return userDAO.login(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean registerUser(UserAccount user){
        try{
            return userDAO.insertUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
