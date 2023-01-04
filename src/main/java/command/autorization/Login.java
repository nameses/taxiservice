package command.autorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.User.User;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Login implements Command{
    @Override
    public PageUrl execute(HttpServletRequest request){
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(600);//10 minutes inactive
        String username = request.getParameter("username");
        String password = EncryptionUtil.getEncrypted(request.getParameter("password"));
        User user = userService.login(username,password);
        if (user == null) {
            return new PageUrl(PageConstants.LOGIN, false,
                    "Wrong login or password");
        }
        session.setAttribute("user", user);
        return new PageUrl(PageConstants.HOMEPAGE, true);
    }
}
