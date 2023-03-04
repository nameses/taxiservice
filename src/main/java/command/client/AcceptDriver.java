package command.client;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.DTO.*;
import models.converters.ClientConverter;
import models.converters.DriverConverter;
import models.converters.OrderConverter;
import models.converters.RouteConverter;
import models.entity.Route;
import models.view.ClientView;
import models.view.DriverView;
import models.view.OrderView;
import models.view.RouteView;
import service.ClientService;
import service.OrderService;
import service.TaxiService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AcceptDriver implements Command {
    OrderService orderService = new OrderService();
    ClientService clientService = new ClientService();
    TaxiService taxiService = new TaxiService();
    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        OrderDTO orderDTO = OrderConverter.toDTO((OrderView) session.getAttribute("order"));
        Boolean ifUsedPoints = (Boolean) request.getAttribute("ifUsedPoints");
        if(ifUsedPoints==null) ifUsedPoints=false;
        orderDTO.setIfUsedPoints(ifUsedPoints);

        RouteDTO routeDTO = RouteConverter.toDTO((RouteView) session.getAttribute("route"));
        ClientDTO clientDTO = clientService.selectByUserID((ClientView) session.getAttribute("client"));
        TaxiDTO taxiDTO = taxiService.findByDriver(new DriverDTO(orderDTO.getDriverID()));

        ClientDTO responseClientDTO = orderService.acceptDriver(orderDTO, clientDTO, routeDTO, taxiDTO);
        if (!responseClientDTO.getSuccess()) {
            return new PageUrl(PageConstants.ACCEPTED_DRIVER_PAGE,
                    false,
                    "Try to accept again.");
        }
        //set client to session
        session.setAttribute("client", ClientConverter.toView(responseClientDTO));
        //update db via clientDAO
        clientService.updateByClientID(responseClientDTO);

        return new PageUrl(PageConstants.ACCEPTED_DRIVER_PAGE, false);
    }
}
