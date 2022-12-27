package service;

import DAO.OrderDAO;
import entity.Taxi;
import entity.TaxiOrder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();
    public List<TaxiOrder> getList(HttpServletRequest request){
        try{
            String orderByString = request.getParameter("orderByString");
            String orderBySort = request.getParameter("orderBySort");
            return orderDAO.selectAllByString(orderByString, orderBySort);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
