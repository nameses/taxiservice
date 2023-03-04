package service;

import DAO.ClientDAO;
import DAO.DriverDAO;
import DAO.UserDAO;
import exceptions.DAOException;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.UserDTO;
import models.converters.ClientConverter;
import models.converters.DriverConverter;
import models.converters.UserConverter;
import models.entity.User;
import models.entity.enums.UserRole;
import models.view.ClientView;
import models.view.DriverView;
import models.view.UserView;

import javax.servlet.http.HttpSession;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    private final DriverDAO driverDAO = new DriverDAO();
    private final ClientDAO clientDAO = new ClientDAO();

    public UserDTO selectByID(DriverDTO driverDTO) throws ServiceException {
        try{
            return userDAO.selectByUserID(driverDTO.getUserID());
        }catch(DAOException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public UserDTO register(UserDTO userDTO) throws ServiceException {
        try {
            UserDTO response = userDAO.validateData(
                    new User(
                            userDTO.getUsername(),
                            userDTO.getFullname(),
                            userDTO.getPhone(),
                            userDTO.getEmail()
                    ));
            if(!response.getSuccess()) return response;
            return userDAO.insert(UserConverter.toEntity(userDTO));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public UserDTO login(HttpSession session, UserDTO userDTO) throws ServiceException {
        try {
            UserDTO responseUserDTO = userDAO.login(UserConverter.toEntity(userDTO));
            if (responseUserDTO != null) {
                session.setAttribute("user", UserConverter.toView(responseUserDTO));
                if (responseUserDTO.getRole() == UserRole.driver) {
                    DriverView driverView = DriverConverter.toView(driverDAO.login(responseUserDTO.getUserID()));
                    session.setAttribute("driver", driverView);
                    session.setAttribute("isDriverBusy",false);
                } else {//client
                    ClientView clientView = ClientConverter.toView(clientDAO.getByUserID(responseUserDTO.getUserID()));
                    session.setAttribute("client", clientView);
                }
                return responseUserDTO;
            } else return new UserDTO(false);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
