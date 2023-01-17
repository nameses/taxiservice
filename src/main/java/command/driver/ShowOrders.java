package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ShowOrders implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
//        OrderService orderService = new OrderService();
//        if (!orderService.show(request)) {
//            return new PageUrl(PageConstants.LIST_ORDERS, false, "Error during saving order");
//        }
//        return new PageUrl(PageConstants.LIST_ORDERS, false);
        return null;
    }
}
