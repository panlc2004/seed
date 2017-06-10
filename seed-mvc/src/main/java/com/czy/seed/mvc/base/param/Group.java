package com.czy.seed.mvc.base.param;

import com.czy.seed.mybatis.base.QueryParams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by 003914[panlc] on 2017-06-09.
 */
public class Group implements Serializable {

    private static final long serialVersionUID = 1853557890332197293L;

    /**
     * 查询参数对象转换
     * @param queryParams 查询参数对象
     */
    public void appendParamGroup(QueryParams queryParams) {
        QueryParams.Criteria criteria = queryParams.or();
        if (this.getLike() != null) {
            for (Map.Entry<String, Object> like : this.getLike().entrySet()) {
                criteria.andLike(like.getKey(), like.getValue().toString());
            }
        }

        if (this.getNotLike() != null) {
            for (Map.Entry<String, Object> notLike : this.getNotLike().entrySet()) {
                criteria.andNotLike(notLike.getKey(), notLike.getValue().toString());
            }
        }

        if (this.getIn() != null) {
            for (Map.Entry<String, List<Object>> in : this.getIn().entrySet()) {
                criteria.andIn(in.getKey(), in.getValue());
            }
        }

        if (this.getNotIn() != null) {
            for (Map.Entry<String, List<Object>> notIn : this.getNotIn().entrySet()) {
                criteria.andNotIn(notIn.getKey(), notIn.getValue());
            }
        }

        if (this.getBetween() != null) {
            for (Map.Entry<String, Between> between : this.getBetween().entrySet()) {
                criteria.andBetween(between.getKey(), between.getValue().getBengin(), between.getValue().getEnd());
            }
        }

        if (this.getNotBetween() != null) {
            for (Map.Entry<String, Between> notBetween : this.getNotBetween().entrySet()) {
                criteria.andNotBetween(notBetween.getKey(), notBetween.getValue().getBengin(), notBetween.getValue().getEnd());
            }
        }

        if (this.getEqualTo() != null) {
            for (Map.Entry<String, Object> equalTo : this.getEqualTo().entrySet()) {
                criteria.andEqualTo(equalTo.getKey(), equalTo.getValue());
            }
        }

        if (this.getNotEqualTo() != null) {
            for (Map.Entry<String, Object> notEqualTo : this.getNotEqualTo().entrySet()) {
                criteria.andNotEqualTo(notEqualTo.getKey(), notEqualTo.getValue());
            }
        }

        if (this.getCondition() != null) {
            criteria.andCondition(this.getCondition());
        }

        if (this.getGreatThan() != null) {
            for (Map.Entry<String, Object> greatThan : this.getGreatThan().entrySet()) {
                criteria.andGreaterThan(greatThan.getKey(), greatThan.getValue());
            }
        }

        if (this.getGreatThanOrEqualTo() != null) {
            for (Map.Entry<String, Object> greatThanOrEqualTo : this.getGreatThanOrEqualTo().entrySet()) {
                criteria.andNotEqualTo(greatThanOrEqualTo.getKey(), greatThanOrEqualTo.getValue());
            }
        }

        if (this.getLessThan() != null) {
            for (Map.Entry<String, Object> lessThan : this.getLessThan().entrySet()) {
                criteria.andNotEqualTo(lessThan.getKey(), lessThan.getValue());
            }
        }

        if (this.getLessThanOrEqualTo() != null) {
            for (Map.Entry<String, Object> lessThanOrEqualTo : this.getLessThanOrEqualTo().entrySet()) {
                criteria.andNotEqualTo(lessThanOrEqualTo.getKey(), lessThanOrEqualTo.getValue());
            }
        }

        if (this.getSelect() != null && this.getSelect().split(",").length > 0) {
            queryParams.selectProperties(this.getSelect().split(","));
        }

        if (this.getOrderBy() != null) {
            for (Map.Entry<String, String> orderBy : this.getOrderBy().entrySet()) {
                QueryParams.OrderBy orderByOpe = queryParams.orderBy(orderBy.getKey());
                String orderByValue = orderBy.getValue();
                if (orderByValue != null && orderByValue.length() > 0) {
                    if ("desc".equals(orderByValue)) {
                        orderByOpe.desc();
                    } else {
                        orderByOpe.asc();
                    }
                } else {
                    orderByOpe.asc();
                }
            }
        }

    }

    private Map<String, Object> like;
    private Map<String, Object> notLike;
    private Map<String, Between> between;
    private Map<String, Between> notBetween;
    private Map<String, List<Object>> in;
    private Map<String, List<Object>> notIn;
    private Map<String, Object> equalTo;
    private Map<String, Object> notEqualTo;
    private Map<String, Object> greatThan;
    private Map<String, Object> greatThanOrEqualTo;
    private Map<String, Object> lessThan;
    private Map<String, Object> lessThanOrEqualTo;
    private String condition;
    private String select;
    private Map<String, String> orderBy;


    public Map<String, Object> getLike() {
        return like;
    }

    public void setLike(Map<String, Object> like) {
        this.like = like;
    }

    public Map<String, Object> getNotLike() {
        return notLike;
    }

    public void setNotLike(Map<String, Object> notLike) {
        this.notLike = notLike;
    }

    public Map<String, Between> getBetween() {
        return between;
    }

    public void setBetween(Map<String, Between> between) {
        this.between = between;
    }

    public Map<String, Between> getNotBetween() {
        return notBetween;
    }

    public void setNotBetween(Map<String, Between> notBetween) {
        this.notBetween = notBetween;
    }

    public Map<String, List<Object>> getIn() {
        return in;
    }

    public void setIn(Map<String, List<Object>> in) {
        this.in = in;
    }

    public Map<String, List<Object>> getNotIn() {
        return notIn;
    }

    public void setNotIn(Map<String, List<Object>> notIn) {
        this.notIn = notIn;
    }

    public Map<String, Object> getEqualTo() {
        return equalTo;
    }

    public void setEqualTo(Map<String, Object> equalTo) {
        this.equalTo = equalTo;
    }

    public Map<String, Object> getNotEqualTo() {
        return notEqualTo;
    }

    public void setNotEqualTo(Map<String, Object> notEqualTo) {
        this.notEqualTo = notEqualTo;
    }

    public Map<String, Object> getGreatThan() {
        return greatThan;
    }

    public void setGreatThan(Map<String, Object> greatThan) {
        this.greatThan = greatThan;
    }

    public Map<String, Object> getGreatThanOrEqualTo() {
        return greatThanOrEqualTo;
    }

    public void setGreatThanOrEqualTo(Map<String, Object> greatThanOrEqualTo) {
        this.greatThanOrEqualTo = greatThanOrEqualTo;
    }

    public Map<String, Object> getLessThan() {
        return lessThan;
    }

    public void setLessThan(Map<String, Object> lessThan) {
        this.lessThan = lessThan;
    }

    public Map<String, Object> getLessThanOrEqualTo() {
        return lessThanOrEqualTo;
    }

    public void setLessThanOrEqualTo(Map<String, Object> lessThanOrEqualTo) {
        this.lessThanOrEqualTo = lessThanOrEqualTo;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public Map<String, String> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Map<String, String> orderBy) {
        this.orderBy = orderBy;
    }

    static class Between {
        private String bengin;
        private String end;

        public String getBengin() {
            return bengin;
        }

        public void setBengin(String bengin) {
            this.bengin = bengin;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }
    }
}
