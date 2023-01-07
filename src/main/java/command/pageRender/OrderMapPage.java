package command.pageRender;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;

import javax.servlet.http.HttpServletRequest;

public class OrderMapPage implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        return new PageUrl(PageConstants.ORDER_MAP,false);
    }
}
