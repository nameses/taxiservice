package command.autorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.UserAccount;
import service.UserService;
import utils.EncryptionUtil;
import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {


    @Override
    public PageUrl execute(HttpServletRequest request){
        UserService userService = new UserService();
        UserAccount user = getUser(request);
        if(!userService.registerUser(user)){
            return new PageUrl(PageConstants.REGISTRATION, false, "Unknown error.");
        }
        return new PageUrl(PageConstants.LOGIN, true);
    }

    private UserAccount getUser(HttpServletRequest request){
        UserAccount user = new UserAccount();
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        //TODO validation
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(EncryptionUtil.getEncrypted(password));
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }
}
