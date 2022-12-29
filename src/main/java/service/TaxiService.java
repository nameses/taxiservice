package service;

import DAO.TaxiDAO;
import entity.Taxi;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TaxiService {
    private final TaxiDAO taxiDAO = new TaxiDAO();
    public List<Taxi> getList(HttpServletRequest request){
        try{
            String orderByString = request.getParameter("orderByString");
            String orderBySort = request.getParameter("orderBySort");
            return taxiDAO.selectAllByString(orderByString, orderBySort);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void updateStatus(Integer id, String toStatus){
        try{
            taxiDAO.updateStatus(id, toStatus);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
