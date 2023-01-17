package command.admin;

import command.Command;
import command.page.PageUrl;

import javax.servlet.http.HttpServletRequest;

public class ListTaxis implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
//        TaxiService taxiService = new TaxiService();
//        HttpSession session = request.getSession();
//        List<Taxi> taxis = taxiService.getList(request);
//        session.setAttribute("listTaxis", taxis);
//        return new PageUrl(PageConstants.LIST_TAXIS, true);
        return null;
    }
}
