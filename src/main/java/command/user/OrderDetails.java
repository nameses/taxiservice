package command.user;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.OrderDTO;
import models.converters.RouteConverter;
import models.entity.Client;
import models.entity.enums.CarCategory;
import models.entity.enums.OrderStatus;
import models.view.ClientView;
import models.view.RouteView;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class OrderDetails implements Command {
    OrderService orderService = new OrderService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        OrderDTO response = orderService.saveOrder(
                buildOrder(request, (ClientView) session.getAttribute("client")),
                RouteConverter.toDTO((RouteView) session.getAttribute("route")));
        if (!response.getStatus()) {
            return new PageUrl(PageConstants.ORDER_DETAILS_PAGE,
                    false,
                    "Error during saving order");
        }
        return new PageUrl(PageConstants.ORDER_OPENED_PAGE_GET, true);
    }

    private OrderDTO buildOrder(HttpServletRequest request, ClientView clientView) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setClientID(clientView.getClientID());
        orderDTO.setOrderOpened(new Timestamp(System.currentTimeMillis()));
        orderDTO.setCarCapacity(Integer.valueOf(request.getParameter("carCapacity")));
        orderDTO.setCarCategory(CarCategory.valueOf(request.getParameter("carCategory")));
        orderDTO.setOrderStatus(OrderStatus.processing);
        return orderDTO;
    }
}
