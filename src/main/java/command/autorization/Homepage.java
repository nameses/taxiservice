package command.autorization;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.UserAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Homepage implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        return new PageUrl(PageConstants.HOMEPAGE,false);
    }
}
