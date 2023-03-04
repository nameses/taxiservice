package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.OrderDTO;
import models.view.OrderView;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EndOrder implements Command {
    OrderService orderService = new OrderService();
    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        Integer orderID = ((OrderView) session.getAttribute("order")).getOrderID();
        session.removeAttribute("order");
        session.setAttribute("isDriverBusy",false);
        orderService.endOrder(new OrderDTO(orderID));

        return new PageUrl(PageConstants.HOME_PAGE, false);
    }
}
