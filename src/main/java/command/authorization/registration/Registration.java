package command.authorization.registration;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import models.entity.User.User;
import models.entity.enums.UserRole;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Registration implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        User user = userService.register(getUser(request));
        if (user == null) {
            return new PageUrl(PageConstants.REG_PAGE, false, "Unknown error.");
        }
        session.setAttribute("user", user);
        return new PageUrl(PageConstants.HOME_PAGE, true);
    }

    protected User getUser(HttpServletRequest request) {
        User user = new User();
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = EncryptionUtil.getEncrypted(request.getParameter("password"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        //TODO validation
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(UserRole.valueOf(role));
        return user;
    }
}
