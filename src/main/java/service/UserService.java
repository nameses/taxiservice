package service;

import DAO.ClientDAO;
import DAO.DriverDAO;
import DAO.UserDAO;
import entity.User.Client;
import entity.User.Driver;
import entity.User.User;
import entity.enums.UserRole;

import javax.servlet.http.HttpSession;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    private final DriverDAO driverDAO = new DriverDAO();
    private final ClientDAO clientDAO = new ClientDAO();
    public boolean login(HttpSession session, String username, String password){
        try{
            User user = userDAO.login(username, password);
            if(user!=null) {
                if (user.getRole() == UserRole.admin) {
                    session.setAttribute("user", user);
                } else if (user.getRole() == UserRole.driver) {
                    Driver driver = driverDAO.getByUserID(user.getUserID());
                    session.setAttribute("driver", driver);
                } else {//client
                    Client client = clientDAO.getByUserID(user.getUserID());
                    session.setAttribute("client", client);
                }
                return true;
            } else return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
