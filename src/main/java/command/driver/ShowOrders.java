package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.SortFilterDTO;
import models.DTO.TaxiDTO;
import models.converters.DriverConverter;
import models.entity.enums.CarCategory;
import models.entity.enums.OrderStatus;
import models.view.DriverView;
import models.view.OrderRouteView;
import service.OrderService;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

public class ShowOrders implements Command {
    OrderService orderService = new OrderService();
    TaxiService taxiService = new TaxiService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        request.setAttribute("listCategories", Arrays.asList(CarCategory.values()));

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
        session.setAttribute("orderList", response.getOrders());
        session.setAttribute("mapOrderIDToRoute", response.getRouteViewMap());
//        if (!) {
//            return new PageUrl(PageConstants.SHOW_ORDERS_PAGE,
//                    false,
//                    "Error during saving order");
//        }
        return new PageUrl(PageConstants.SHOW_ORDERS_PAGE, false);
    }

    private SortFilterDTO processSorting(HttpServletRequest request, HttpSession session) {
        String category = request.getParameter("category");
        String maxCapacity = request.getParameter("maxCapacity");

        String orderByKey = request.getParameter("inputKey");
        String orderByValue = request.getParameter("inputValue");

        SortFilterDTO sortFilterDTO = (SortFilterDTO) session.getAttribute("sortFilter");
        if (sortFilterDTO == null) sortFilterDTO = new SortFilterDTO();

        //filters
        if (category != null)
            sortFilterDTO.addFilter("carcategory", category);
        if (maxCapacity != null)
            sortFilterDTO.addFilter("maxCapacity", Integer.valueOf(maxCapacity));
        //sorting
        if (orderByKey != null && orderByValue != null) {
            sortFilterDTO.setOrderBy(orderByKey, orderByValue);
        }

        session.setAttribute("sortFilter", sortFilterDTO);
        return sortFilterDTO;
    }
}
