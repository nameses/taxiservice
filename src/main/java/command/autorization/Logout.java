package command.autorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;

import javax.servlet.http.HttpServletRequest;

public class Logout implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        request.getSession().invalidate();
        return new PageUrl(PageConstants.MAINPAGE, true);
    }
}
