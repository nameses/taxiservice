package command.pageRender;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;

import javax.servlet.http.HttpServletRequest;

public class Regpage implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        return new PageUrl(PageConstants.REG_PAGE,false);
    }
}
