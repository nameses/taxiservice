package service;

import DAO.UserDAO;
import entity.UserAccount;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public Boolean registerUser(UserAccount user){
        try{
            return userDAO.insertUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
