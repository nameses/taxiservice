package models.DTO;

import java.util.Map;

public class SortFilterDTO {
    private Pair orderBy;
    private Pair filter;

    public String getOrderBy() {
        if (orderBy!=null && orderBy.key!=null && orderBy.value!=null)
            return orderBy.key + " " + orderBy.value;
        return null;
    }

    public void setOrderBy(Pair orderBy) {
        this.orderBy = orderBy;
    }

    public String getFilter() {
        if(filter!=null && filter.key!=null && filter.value!=null)
            return filter.key + "=" + filter.value;
        return null;
    }

    public void setFilter(Pair filter) {
        this.filter = filter;
    }

    public static class Pair {
        String key;
        String value;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
