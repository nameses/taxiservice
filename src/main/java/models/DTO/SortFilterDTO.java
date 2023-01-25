package models.DTO;

import java.util.HashMap;
import java.util.Map;

public class SortFilterDTO {
    private Map<String, String> orderBy; // first - column to sort, second - order
    private Map<String, Object> filter;// = new HashMap<>();

    public void setOrderBy(String orderByName, String orderBySort) {
        //order by statement can only be single
        orderBy = new HashMap<>();
        orderBy.put(orderByName, orderBySort);
    }

    public void addFilter(String filterName, Object filterValue) {
        if(filter==null) filter = new HashMap<>();
        filter.put(filterName, filterValue);
    }

    public Map<String, String> getOrderByMap() {
        return orderBy;
    }

    public Map<String, Object> getFilterMap() {
        return filter;
    }
}
