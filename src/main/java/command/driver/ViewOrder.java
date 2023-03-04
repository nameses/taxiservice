package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.converters.RouteConverter;
import models.view.OrderView;
import models.view.RouteView;
import service.ClientService;
import service.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ViewOrder implements Command {
    RouteService routeService = new RouteService();
    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        OrderView orderView = (OrderView) session.getAttribute("order");
        Integer orderID;
        if(orderView!=null){
            orderID=orderView.getOrderID();
            RouteView routeView = RouteConverter.toView(routeService.selectByOrderID(orderID));
            request.setAttribute("route",routeView);
        }
        return new PageUrl(PageConstants.VIEW_ORDER_PAGE, false);
    }
}