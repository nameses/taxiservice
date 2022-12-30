package service;

import DAO.TaxiDAO;
import entity.Taxi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TaxiService {
    private final TaxiDAO taxiDAO = new TaxiDAO();

    public void deleteTaxi(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        taxiDAO.deleteTaxi(id);
    }
    public Boolean newTaxi(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            Taxi taxi = new Taxi();
            taxi.setDriverName(request.getParameter("driverName"));
            taxi.setLicensePlate(request.getParameter("licensePlate"));
            taxi.setFare(Integer.valueOf(request.getParameter("fare")));
            taxi.setCategory(request.getParameter("category"));
            taxi.setCapacity(request.getParameter("capacity"));
            taxi.setStatus("inactive");
            session.setAttribute("taxi", taxi);
            return taxiDAO.insertTaxi(taxi);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
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
