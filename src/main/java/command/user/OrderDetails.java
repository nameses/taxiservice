package command.user;

import DAO.OrderDAO;
import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import service.OrderService;
import service.RouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class OrderDetails implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        RouteService routeService = new RouteService();
        PageUrl page = routeCheck(Integer.parseInt(request.getParameter("routeLength")));
        if(page!=null) return page;
        if (!routeService.saveRoute(request))
            return new PageUrl(PageConstants.ORDER_MAP, false, "Error during saving order details");
        return new PageUrl(PageConstants.ORDER_DETAILS, false);
    }

    private PageUrl routeCheck(Integer routeLength) {
        if (routeLength < 3000)
            return new PageUrl(PageConstants.ORDER_MAP, false, "Minimal order is at least 3km");
        else if (routeLength > 30000)
            return new PageUrl(PageConstants.ORDER_MAP, false, "Maximum order is 30km");
        return null;
    }
}
