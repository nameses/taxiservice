package command.pageRender;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.OrderDTO;
import models.converters.DriverConverter;
import models.converters.OrderConverter;
import models.entity.enums.OrderStatus;
import models.view.DriverView;
import models.view.OrderView;
import service.DriverService;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Homepage implements Command {
    DriverService driverService = new DriverService();
    OrderService orderService = new OrderService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Integer orderID = null;
        OrderView orderView = ((OrderView) session.getAttribute("order"));
        if(orderView==null){
            return new PageUrl(PageConstants.HOME_PAGE, false);
        } else{
            orderID = orderView.getOrderID();
        }

        if (orderID != null) orderView = orderService.selectByID(new OrderDTO(orderID));

        if(orderView.getOrderStatus()== OrderStatus.completed){
            session.removeAttribute("order");
        }
        else{
            session.setAttribute("order", orderView);
            if(orderView.getDriverID()==null) {
                session.setAttribute("isDriverBusy", false);
                session.removeAttribute("currentOrderID");
            }
        }
        return new PageUrl(PageConstants.HOME_PAGE, false);
    }
}
