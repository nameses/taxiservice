package command.pageRender;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowOrdersPage implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("sortFilter");
//        session.removeAttribute("orderList");
//        session.removeAttribute("mapOrderIDToRoute");
        return new PageUrl("/driver?command=showOrders", false);
    }
}
