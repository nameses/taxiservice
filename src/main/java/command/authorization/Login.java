package command.authorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Login implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(600);//10 minutes inactive
        String username = request.getParameter("username");
        String password = EncryptionUtil.getEncrypted(request.getParameter("password"));
        boolean ifValid = userService.login(session, username, password);
        if (!ifValid) {
            return new PageUrl(PageConstants.LOGIN, false, "Wrong login or password");
        }
        return new PageUrl(PageConstants.HOME_PAGE, true);
    }
}