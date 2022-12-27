package service;

import DAO.TaxiDAO;
import entity.Taxi;

import java.util.List;

public class TaxiService {
    private final TaxiDAO taxiDAO = new TaxiDAO();
    public List<Taxi> getList(String orderByString, String orderBySort){
        try{
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
