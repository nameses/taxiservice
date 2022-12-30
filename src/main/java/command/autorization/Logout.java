package command.autorization;

import com.sun.tools.javac.Main;
import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.UserAccount;
import service.UserService;
import utils.EncryptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request){
        request.getSession().invalidate();
        return new PageUrl(PageConstants.MAINPAGE, true);
    }
}
