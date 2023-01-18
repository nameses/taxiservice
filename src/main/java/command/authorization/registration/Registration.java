package command.authorization.registration;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.UserDTO;
import models.converters.UserConverter;
import models.entity.User;
import models.entity.enums.UserRole;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Registration implements Command {
    UserService userService = new UserService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        UserDTO userDTO = userService.register(buildUser(request));
        if (!userDTO.getStatus())
            return new PageUrl(PageConstants.REG_PAGE,
                    false,
                    "Unknown error.");
        session.setAttribute("user", UserConverter.toView(userDTO));
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
