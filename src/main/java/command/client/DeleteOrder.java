package command.client;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.view.OrderView;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteOrder implements Command {
    OrderService orderService = new OrderService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        OrderView orderView = (OrderView) session.getAttribute("order");

        orderService.cancelOrder(orderView.getOrderID());
//        orderService.cancelDriverFromOrder(orderView.getOrderID());

        session.removeAttribute("order");
        return new PageUrl(PageConstants.HOME_PAGE, true);
    }
}
