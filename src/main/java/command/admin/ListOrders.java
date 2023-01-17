package command.admin;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;

import javax.servlet.http.HttpServletRequest;

public class ListOrders implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
//        OrderService orderService = new OrderService();
//        HttpSession session = request.getSession();
//        List<Order> orders = orderService.getList(request);
//        session.setAttribute("listOrders", orders);
        return new PageUrl(PageConstants.LIST_ORDERS, true);
    }
}