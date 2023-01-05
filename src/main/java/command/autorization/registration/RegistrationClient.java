package command.autorization.registration;

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

public class RegistrationClient extends Registration implements Command {
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
        //TODO validation
        client.setBonusPoints(0);
        User user = (User) request.getSession().getAttribute("user");
        client.setUser(user);
        client.setUserID(user.getUserID());
        return client;
    }
}
