package command.authorization.registration;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import models.entity.User.Client;
import models.entity.User.User;
import service.ClientService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationClient extends Registration implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        ClientService clientService = new ClientService();
        Client client = getClient(request);
        if(!clientService.register(client)){
            return new PageUrl(PageConstants.REG_PAGE, false, "Unknown error.");
        }
        return new PageUrl(PageConstants.LOGIN_PAGE, true);
    }
    private Client getClient(HttpServletRequest request){
        Client client = new Client();
        //TODO validation
        client.setBonusPoints(0);
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().removeAttribute("user");
        client.setUser(user);
        client.setUserID(user.getUserID());
        return client;
    }
}
