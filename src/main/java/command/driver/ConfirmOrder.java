package command.driver;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class ConfirmOrder implements Command {

    @Override
    public PageUrl execute(HttpServletRequest request) throws ServiceException {



        return new PageUrl(PageConstants.CONFIRM_ORDER_PAGE, false);
    }
}
