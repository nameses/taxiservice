package command.pageRender;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.converters.OrderConverter;
import models.entity.Order;
import models.entity.enums.OrderStatus;
import models.view.OrderView;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrderMainPage implements Command {
    OrderService orderService = new OrderService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {

        HttpSession session = request.getSession();
        OrderView response = orderService.selectByID(OrderConverter.toDTO((OrderView) session.getAttribute("order")));
        session.setAttribute("order", response);
        OrderStatus orderStatus = response.getOrderStatus();
        if(orderStatus!=null) session.setAttribute("orderStatus", response.getOrderStatus().name());
        else session.setAttribute("orderStatus", null);
        return new PageUrl(PageConstants.ORDER_MAIN_PAGE, false);
    }
}
