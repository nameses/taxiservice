package command.user;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.OrderDTO;
import models.DTO.RouteDTO;
import models.converters.RouteConverter;
import models.entity.Order;
import models.entity.User.Client;
import models.entity.enums.CarCategory;
import models.entity.enums.OrderStatus;
import models.view.RouteView;
import service.OrderService;
import service.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class OrderDetails implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        OrderDTO response = orderService.saveOrder(buildOrder(request),
                RouteConverter.toDTO((RouteView) session.getAttribute("route")));
        if (!response.getStatus()) {
            return new PageUrl(PageConstants.ORDER_DETAILS_PAGE,
                    false,
                    "Error during saving order");
        }
        return new PageUrl(PageConstants.ORDER_OPENED_PAGE_GET, true);
    }

    private OrderDTO buildOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setClientID(((Client) session.getAttribute("client")).getClientID());
        orderDTO.setOrderOpened(new Timestamp(System.currentTimeMillis()));
        orderDTO.setCarCapacity(Integer.valueOf(request.getParameter("carCapacity")));
        orderDTO.setCarCategory(CarCategory.valueOf(request.getParameter("carCategory")));
        orderDTO.setOrderStatus(OrderStatus.processing);
        return orderDTO;
    }
}
