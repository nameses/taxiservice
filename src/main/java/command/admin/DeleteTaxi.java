package command.admin;

import command.Command;
import command.page.PageUrl;
import service.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class DeleteTaxi implements Command {
    @Override
    public PageUrl execute(HttpServletRequest request) {
        TaxiService taxiService = new TaxiService();
        taxiService.deleteTaxi(request);
        return new PageUrl("/controller?command=listTaxis", false);
    }
}