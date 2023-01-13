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
//        if (!orderService.saveOrder(request)) {
//            return new PageUrl(PageConstants.ORDER_DETAILS_PAGE, false, "Error during saving order");
//        }
//        return new PageUrl(PageConstants.ORDER_OPENED_PAGE_GET, true);
        return null;
    }
}
