package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.TaxiDTO;
import models.converters.DriverConverter;
import models.entity.enums.OrderStatus;
import models.view.DriverView;
import models.view.OrderRouteView;
import service.OrderService;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowOrders implements Command {
    OrderService orderService = new OrderService();
    TaxiService taxiService = new TaxiService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        DriverDTO driverDTO = DriverConverter.toDTO(
                (DriverView) session.getAttribute("driver"));
        //check if driver has a registered car
        TaxiDTO taxiDTO = taxiService.findByDriver(driverDTO);
        if (!taxiDTO.getSuccess())
            return new PageUrl(PageConstants.SHOW_ORDERS_PAGE,
                    false,
                    "You need to register car first!");

        OrderRouteView response = orderService.showOrders(
                OrderStatus.processing,
                driverDTO,
                taxiDTO);
        request.setAttribute("orderList", response.getOrders());
        request.setAttribute("mapOrderIDToRoute", response.getRouteViewMap());
//        if (!) {
//            return new PageUrl(PageConstants.SHOW_ORDERS_PAGE,
//                    false,
//                    "Error during saving order");
//        }
        return new PageUrl(PageConstants.SHOW_ORDERS_PAGE, false);
    }
}
