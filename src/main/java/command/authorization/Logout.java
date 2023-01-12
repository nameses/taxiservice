package command.authorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.Order;
import service.OrderService;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if(order!=null){
            OrderService orderService = new OrderService();
            orderService.cancelOrder(order.getOrderID());
        }
        session.invalidate();
        return new PageUrl(PageConstants.MAIN_PAGE, true);
    }
}