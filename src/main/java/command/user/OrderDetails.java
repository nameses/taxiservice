package command.user;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class OrderDetails implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        Integer routeLength = Integer.valueOf((String) request.getAttribute("routeLength"));
        if(routeLength<3000){
            return new PageUrl(PageConstants.ORDER_MAP, false,"Minimal order is at least 3km");
        }
        return new PageUrl(PageConstants.ORDER_DETAILS,true);
    }
}
