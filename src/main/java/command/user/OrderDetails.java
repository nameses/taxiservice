package command.user;

import command.Command;
import command.page.PageUrl;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class OrderDetails implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        request.getAttribute("")

    }
}
