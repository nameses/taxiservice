package models.view;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OrderRouteView implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //orders
    private List<OrderView> orders;
    // map orderID to route
    private Map<Integer, RouteView> routeViewMap;

    public OrderRouteView(List<OrderView> orders, Map<Integer, RouteView> routeViewMap) {
        this.orders = orders;
        this.routeViewMap = routeViewMap;
    }

    public OrderRouteView() {
    }

    public List<OrderView> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderView> orders) {
        this.orders = orders;
    }

    public Map<Integer, RouteView> getRouteViewMap() {
        return routeViewMap;
    }

    public void setRouteDTOMap(Map<Integer, RouteView> routeViewMap) {
        this.routeViewMap = routeViewMap;
    }
}
