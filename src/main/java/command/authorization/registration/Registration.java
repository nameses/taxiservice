package command.authorization.registration;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.User.User;
import entity.enums.UserRole;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Registration implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        UserService userService = new UserService();
        User user = userService.register(getUser(request));
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        if(user==null){
            return new PageUrl(PageConstants.REG_PAGE, false, "Unknown error.");
        }
        return new PageUrl(PageConstants.HOME_PAGE, true);
//        if(role==UserRole.client){
//            return new PageUrl(PageConstants.REGISTRATION_CLIENT, false);
//        } else if(role==UserRole.driver){
//            return new PageUrl(PageConstants.REGISTRATION_DRIVER, false);
//        } else{
//            return new PageUrl(PageConstants.REGISTRATION, false,"Unknown error");
//        }
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
