package command.user;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class OrderDetails implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        int routeLength = Integer.parseInt(request.getParameter("routeLength"));
        String[] startMarker = request.getParameterValues("startMarker[]");
        String[] finalMarker = request.getParameterValues("finalMarker[]");
        System.out.println(Arrays.stream(startMarker).toList());
        System.out.println(Arrays.stream(finalMarker).toList());
        System.out.println(routeLength);
        if(routeLength<3000){
            return new PageUrl(PageConstants.ORDER_MAP, false,"Minimal order is at least 3km");
        }
        return new PageUrl(PageConstants.ORDER_DETAILS,true);
    }
}
