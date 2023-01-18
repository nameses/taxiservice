package service;

import DAO.ClientDAO;
import DAO.DriverDAO;
import DAO.UserDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.UserDTO;
import models.converters.UserConverter;
import models.entity.Client;
import models.entity.Driver;
import models.entity.User;
import models.entity.enums.UserRole;

import javax.servlet.http.HttpSession;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    private final DriverDAO driverDAO = new DriverDAO();
    private final ClientDAO clientDAO = new ClientDAO();

    public UserDTO register(UserDTO userDTO) throws ServiceException {
        try {
            UserDTO response = userDAO.validateData(
                    new User(
                            userDTO.getUsername(),
                            userDTO.getFullname(),
                            userDTO.getPhone(),
                            userDTO.getEmail()
                    ));
            if(!response.getStatus()) return response;
            return userDAO.insert(UserConverter.toEntity(userDTO));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public boolean login(HttpSession session, String username, String password) {
        try {
            User user = userDAO.login(username, password);
            if (user != null) {
                session.setAttribute("user", user);
//                if (user.getRole() == UserRole.admin)else
                if (user.getRole() == UserRole.driver) {
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
