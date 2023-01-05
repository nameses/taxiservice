package command.autorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.User.Client;
import entity.User.User;
import entity.enums.UserRole;
import service.ClientService;
import service.UserService;
import utils.EncryptionUtil;
import javax.servlet.http.HttpServletRequest;

public class RegistrationClient implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        ClientService clientService = new ClientService();
        Client client = getClient(request);
        if(!clientService.register(client)){
            return new PageUrl(PageConstants.REGISTRATION, false, "Unknown error.");
        }
        return new PageUrl(PageConstants.LOGIN, true);
    }
    private Client getClient(HttpServletRequest request){
        Client client = new Client();
        String bonusPoints = request.getParameter("bonusPoints");
        //TODO validation
        client.setBonusPoints(Integer.valueOf(bonusPoints));
        client.setUser(getUser(request));
        client.setUserID(client.getUser().getUserID());
        return client;
    }

    private User getUser(HttpServletRequest request){
        User user = new User();
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        //TODO validation
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(EncryptionUtil.getEncrypted(password));
        user.setPhone(phone);
        user.setEmail(email);
        user.setRole(UserRole.valueOf(role));
        return user;
    }
}
