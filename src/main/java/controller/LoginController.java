package controller;

import command.Command;
import command.CommandFactory;
import command.CommandType;
import command.page.PageUrl;
import entity.enums.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        process(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        process(request,response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response){
        try{
            CommandFactory factory = new CommandFactory();
            Command command = factory.getCommand(request);
            if(command== CommandType.getCommand("registration")){
                PageUrl page = command.execute(request);
                if(UserRole.valueOf(request.getParameter("role"))==UserRole.client){
                    command=CommandType.getCommand("registrationClient");
                }else command=CommandType.getCommand("registrationDriver");
            }
            PageUrl page = command.execute(request);
            if (page.isRedirection()) {
                redirect(page, request, response);
            } else {
                forward(page, request, response);
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
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
        if (message!=null)
            request.setAttribute("MESSAGE", message);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page.getUrlPath());
        requestDispatcher.forward(request, response);
    }
}
