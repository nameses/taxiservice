package command.client;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.OrderDTO;
import models.converters.OrderConverter;
import models.view.OrderView;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeclineDriver implements Command {
    OrderService orderService = new OrderService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        OrderDTO orderDTO = OrderConverter.toDTO((OrderView) session.getAttribute("order"));

        if (!orderService.declineDriver(orderDTO).getSuccess()) {
            return new PageUrl(PageConstants.DECLINE_DRIVER_PAGE,
                    false,
                    "Try to delete again.");
        }
        return new PageUrl(PageConstants.DECLINE_DRIVER_PAGE, false);
    }
}
