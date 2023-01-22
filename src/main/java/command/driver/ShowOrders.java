package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.SortFilterDTO;
import models.DTO.TaxiDTO;
import models.converters.DriverConverter;
import models.entity.enums.OrderStatus;
import models.view.DriverView;
import models.view.OrderRouteView;
import service.OrderService;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ShowOrders implements Command {
    OrderService orderService = new OrderService();
    TaxiService taxiService = new TaxiService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();

        SortFilterDTO sortFilterDTO = this.processSorting(request, session);

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
                taxiDTO,
                sortFilterDTO);
        request.setAttribute("orderList", response.getOrders());
        request.setAttribute("mapOrderIDToRoute", response.getRouteViewMap());
//        if (!) {
//            return new PageUrl(PageConstants.SHOW_ORDERS_PAGE,
//                    false,
//                    "Error during saving order");
//        }
        return new PageUrl(PageConstants.SHOW_ORDERS_PAGE, false);
    }

    private SortFilterDTO processSorting(HttpServletRequest request, HttpSession session) {
        SortFilterDTO sortFilterDTO = (SortFilterDTO) session.getAttribute("sortFilterDTO");
        if (sortFilterDTO == null) sortFilterDTO = new SortFilterDTO();
        String orderByElement = request.getParameter("orderByElement");
        String orderBySorting = request.getParameter("orderBySorting");
        String filterByElement = request.getParameter("filterByElement");
        String filterByValue = request.getParameter("filterByValue");
        if (orderByElement != null && orderBySorting != null) {
            sortFilterDTO.setFilter(new SortFilterDTO.Pair(orderByElement, orderBySorting));
        }
        if (filterByElement != null && filterByValue != null) {
            sortFilterDTO.setOrderBy(new SortFilterDTO.Pair(filterByElement, filterByValue));
        }
        session.setAttribute("sortFilterDTO",sortFilterDTO);
        return sortFilterDTO;
    }
}
