package command.admin;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.Taxi;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UpdateTaxiStatus implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        TaxiService taxiService = new TaxiService();
        String toStatus = request.getParameter("toStatus");
        Integer id = Integer.valueOf(request.getParameter("id"));
        taxiService.updateStatus(id, toStatus);
        PageUrl pageUrl = new ListTaxis().execute(request);
        return new PageUrl(PageConstants.LIST_TAXIS, true);
    }
}
