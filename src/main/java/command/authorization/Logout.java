package command.authorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import models.entity.Order;
import models.view.OrderView;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        HttpSession session = request.getSession();
//        OrderView orderView = (OrderView) session.getAttribute("order");
//        if(orderView!=null){
//            OrderService orderService = new OrderService();
//            orderService.cancelOrder(orderView.getOrderID());
//        }
        session.invalidate();
        return new PageUrl(PageConstants.MAIN_PAGE, true);
    }
}