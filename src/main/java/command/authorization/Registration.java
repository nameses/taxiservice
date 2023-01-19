package command.authorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.ClientDTO;
import models.DTO.DriverDTO;
import models.DTO.UserDTO;
import models.converters.UserConverter;
import models.entity.Client;
import models.entity.User;
import models.entity.enums.UserRole;
import service.ClientService;
import service.DriverService;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Registration implements Command {
    UserService userService = new UserService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        UserDTO userDTO = userService.register(buildUser(request));
        if (!userDTO.getSuccess())
            return new PageUrl(PageConstants.REG_PAGE,
                    false,
                    String.join("\n", userDTO.getMessages()));

        if (userDTO.getRole() == UserRole.driver) {
            DriverService driverService = new DriverService();

            DriverDTO driverDTO = new DriverDTO();
            driverDTO.setUserID(userDTO.getUserID());

            if (!driverService.register(driverDTO).getSuccess()) {
                return new PageUrl(PageConstants.REG_PAGE,
                        false,
                        "Unknown error.");
            }
        } else if (userDTO.getRole() == UserRole.client) {
            ClientService clientService = new ClientService();

            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setUserID(userDTO.getUserID());

            if (!clientService.register(clientDTO).getSuccess()) {
                return new PageUrl(PageConstants.REG_PAGE,
                        false,
                        "Unknown error.");
            }
        }

        return new PageUrl(PageConstants.LOGIN_PAGE_GET, true);
    }

    protected UserDTO buildUser(HttpServletRequest request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFullname(request.getParameter("fullname"));
        userDTO.setUsername(request.getParameter("username"));
        userDTO.setPassword(EncryptionUtil.getEncrypted(request.getParameter("password")));
        userDTO.setPhone(request.getParameter("phone"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setRole(UserRole.valueOf(request.getParameter("role")));
        return userDTO;
    }
}
