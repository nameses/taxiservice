package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.view.OrderView;
import models.view.RouteView;
import service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ViewOrder implements Command {

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {
        return new PageUrl(PageConstants.VIEW_ORDER_PAGE, false);
    }
}