package command.authorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.UserDTO;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Login implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(6000);//10 minutes inactive

        String username = request.getParameter("username");
        String password = EncryptionUtil.getEncrypted(request.getParameter("password"));

        UserDTO userDTO = userService.login(session, new UserDTO(username, password));
        if (!userDTO.getSuccess()) {
            return new PageUrl(PageConstants.LOGIN_PAGE, false, "Wrong login or password");
        }
        return new PageUrl(PageConstants.HOME_PAGE, true);
    }
}
