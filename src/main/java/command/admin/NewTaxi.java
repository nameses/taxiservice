package command.admin;

import command.Command;
import command.page.PageConstants;
import command.page.PageUrl;
import entity.Taxi;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class NewTaxi implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        TaxiService taxiService = new TaxiService();
        taxiService.newTaxi(request);
        return new PageUrl("/controller?command=listTaxis", false);
    }
}