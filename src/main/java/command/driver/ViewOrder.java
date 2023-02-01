package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.view.OrderView;
import models.view.RouteView;
import service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ViewOrder implements Command {
    ClientService clientService = new ClientService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        List<OrderView> orderList = (List<OrderView>) session.getAttribute("orderList");
        Map<Integer, RouteView> mapOrderIDToRoute = (Map<Integer, RouteView>) session.getAttribute("mapOrderIDToRoute");

        if (orderList == null || mapOrderIDToRoute == null) {
            return new PageUrl(PageConstants.VIEW_ORDER_PAGE,
                    false,
                    "Order not found.");
        }

        Integer id = Integer.valueOf(request.getParameter("orderid"));
        formOrderRouteView(request, orderList, mapOrderIDToRoute, id);

        return new PageUrl(PageConstants.VIEW_ORDER_PAGE, false);
    }

    public void formOrderRouteView(HttpServletRequest request, List<OrderView> listOrderView,
                                   Map<Integer, RouteView> mapOrderIDToRoute, Integer id) {
        request.setAttribute("order", listOrderView.stream()
                .filter((order) -> order.getOrderID().equals(id))
                .findFirst()
                .orElse(null));
        request.setAttribute("route", mapOrderIDToRoute.get(id));
    }
}