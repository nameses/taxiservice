package command.client;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.DriverDTO;
import models.DTO.UserDTO;
import models.converters.DriverConverter;
import models.converters.RouteConverter;
import models.converters.TaxiConverter;
import models.converters.UserConverter;
import models.view.*;
import service.DriverService;
import service.RouteService;
import service.TaxiService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ViewProposition implements Command {
    DriverService driverService = new DriverService();
    UserService userService = new UserService();
    TaxiService taxiService = new TaxiService();
    RouteService routeService = new RouteService();

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        //route is not needed
        //driver from dao to request
        //order in session
        HttpSession session = request.getSession();
        OrderView orderView = (OrderView) session.getAttribute("order");

        DriverDTO driverDTO = new DriverDTO(orderView.getDriverID());

        RouteView routeView = RouteConverter.toView(routeService.selectByOrderID(orderView.getOrderID()));

        DriverView driverView = DriverConverter.toView(driverService.selectByID(driverDTO));
        driverDTO = DriverConverter.toDTO(driverView);

        UserView userView = UserConverter.toView(userService.selectByID(driverDTO));
        TaxiView taxiView = TaxiConverter.toView(taxiService.findByDriver(driverDTO));

        request.setAttribute("driverDetails", userView);
        request.setAttribute("driverAccount", driverView);
        request.setAttribute("driverTaxi", taxiView);

        request.setAttribute("cost", routeView.getLength() / 1000 * taxiView.getFare());

        return new PageUrl(PageConstants.VIEW_PROPOSITION, false);
    }
}
