package models.DTO;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SortFilterDTO {
    private Map<String, String> orderBy; // first - column to sort, second - order
    private Map<String, Object> filter;// = new HashMap<>();
    private Set<String> uniqueFilterKeys;

    public SortFilterDTO() {
        orderBy = new HashMap<>();
        filter = new HashMap<>();
        uniqueFilterKeys = new HashSet<>();
    }

    public void setOrderBy(String orderByName, String orderBySort) {
        //order by statement can only be single
        orderBy = new HashMap<>();
        orderBy.put(orderByName, orderBySort);
    }

    public void addFilter(String filterName, Object filterValue) {
        if (!uniqueFilterKeys.add(filterName)) {
            filter.replace(filterName, filterValue);
        } else filter.put(filterName, filterValue);
    }

    public Map<String, String> getOrderByMap() {
        return orderBy;
    }

    public Map<String, Object> getFilterMap() {
        return filter;
    }
}
