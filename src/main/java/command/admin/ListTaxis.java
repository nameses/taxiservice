package command.admin;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.Taxi;
import entity.UserAccount;
import service.TaxiService;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListTaxis implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        TaxiService taxiService = new TaxiService();
        HttpSession session = request.getSession();
        List<Taxi> taxis = taxiService.getList(request);
        session.setAttribute("listTaxis", taxis);
        return new PageUrl(PageConstants.LIST_TAXIS, true);
    }
}
