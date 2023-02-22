package command.client;

import command.Command;
import command.page.PageUrl;
import exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AcceptDriver implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) throws ServletException, ServiceException {
        return null;
    }
}
