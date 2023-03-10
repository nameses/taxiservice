package controller;

import command.Command;
import command.CommandFactory;
import command.CommandType;
import command.page.PageUrl;
import exceptions.ServiceException;
import models.entity.enums.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = {"/login", "/app", "/admin", "/driver", "/client","/main"})
public class Controller extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CommandFactory factory = new CommandFactory();
            Command command = factory.getCommand(request);
            PageUrl page = command.execute(request);
            if (page.isRedirection())
                redirect(page, request, response);
            else
                forward(page, request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private void redirect(PageUrl page, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String url = page.getUrlPath();
        response.sendRedirect(request.getContextPath() + url);
    }

    private void forward(PageUrl page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = page.getMessage();
        if (message != null)
            request.setAttribute("MESSAGE", message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page.getUrlPath());
        requestDispatcher.forward(request, response);
    }
}
