package command.autorization;

import command.Command;
import command.page.PageUrl;
import entity.UserAccount;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration implements Command {


    @Override
    public PageUrl execute(HttpServletRequest request) throws ServletException {
        UserService userService = new UserService();
        UserAccount client = getUser(request);
    }

    private UserAccount getUser(HttpServletRequest request){
        UserAccount user = new UserAccount();
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = EncryptionUtil.getEncrypted(request.getParameter("password"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        //TODO validation
        
    }
}
