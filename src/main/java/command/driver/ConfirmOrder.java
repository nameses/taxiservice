package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.OrderDTO;
import models.entity.Order;
import models.entity.enums.OrderStatus;
import models.view.DriverView;
import models.view.OrderView;
import models.view.RouteView;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ConfirmOrder implements Command {
    OrderService orderService = new OrderService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        //You confirmed an order, wait until a client accept it!

        HttpSession session = request.getSession();
        Integer orderID = Integer.valueOf(request.getParameter("orderid"));
        // driver status "isBusy" now = true
        session.setAttribute("isDriverBusy", true);
        // order status must = confirmation
        OrderDTO responseUpdateOrderStatus = orderService.updateOrderStatus(
                buildOrderDTO(orderID, OrderStatus.confirmation)
        );
        if (!responseUpdateOrderStatus.getSuccess())
            return new PageUrl(PageConstants.SHOW_ORDERS_PAGE,
                    false,
                    responseUpdateOrderStatus.getMessage());
        // order driver update
        OrderDTO responseUpdateDriverID = orderService.updateDriverID(
                buildOrderDTO(orderID, ((DriverView) session.getAttribute("driver")).getDriverID())
        );
        if (!responseUpdateDriverID.getSuccess())
            return new PageUrl(PageConstants.SHOW_ORDERS_PAGE,
                    false,
                    responseUpdateDriverID.getMessage());


        List<OrderView> orderList = (List<OrderView>) session.getAttribute("orderList");
        Map<Integer, RouteView> mapOrderIDToRoute = (Map<Integer, RouteView>) session.getAttribute("mapOrderIDToRoute");

        if (orderList == null || mapOrderIDToRoute == null) {
            return new PageUrl(PageConstants.VIEW_ORDER_PAGE,
                    false,
                    "Order not found.");
        }

        formOrderRouteView(session, orderList, mapOrderIDToRoute, orderID);

        return new PageUrl(PageConstants.CONFIRM_ORDER_PAGE, false);
    }

    public void formOrderRouteView(HttpSession session, List<OrderView> listOrderView,
                                   Map<Integer, RouteView> mapOrderIDToRoute, Integer id) {
        session.setAttribute("order", listOrderView.stream()
                .filter((order) -> order.getOrderID().equals(id))
                .findFirst()
                .orElse(null));
        session.setAttribute("route", mapOrderIDToRoute.get(id));
        session.removeAttribute("orderList");
        session.removeAttribute("mapOrderIDToRoute");
    }

    private OrderDTO buildOrderDTO(Integer id, Integer driverID) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderID(id);
        orderDTO.setDriverID(driverID);
        return orderDTO;
    }

    private OrderDTO buildOrderDTO(Integer id, OrderStatus orderStatus) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderID(id);
        orderDTO.setOrderStatus(orderStatus);
        return orderDTO;
    }
}
