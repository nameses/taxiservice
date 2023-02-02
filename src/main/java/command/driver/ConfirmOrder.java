package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.OrderDTO;
import models.entity.Order;
import models.entity.enums.OrderStatus;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConfirmOrder implements Command {
    OrderService orderService = new OrderService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        //You confirmed an order, wait until a client accept it!

        HttpSession session = request.getSession();
        Integer orderID = Integer.valueOf(request.getParameter("orderid"));
        // driver status "isBusy" now = true
        session.setAttribute("isDriverBusy", true);
        session.setAttribute("currentOrderID", orderID);
        // order status must = confirmation
        OrderDTO response = orderService.updateOrderStatus(
                buildOrderDTO(orderID, OrderStatus.confirmation)
        );
        return new PageUrl(PageConstants.CONFIRM_ORDER_PAGE, false);
    }

    private OrderDTO buildOrderDTO(Integer id, OrderStatus orderStatus) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderID(id);
        orderDTO.setOrderStatus(orderStatus);
        return orderDTO;
    }
}
