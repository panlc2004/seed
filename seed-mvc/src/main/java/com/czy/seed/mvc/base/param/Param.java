package com.czy.seed.mvc.base.param;

import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.tool.NullUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PLC on 2017/6/10.
 */
public class Param {

    private List<Group> or;
    private String selectProperties;
    private LinkedHashMap<String, String> orderBy;

    public List<Group> getOr() {
        return or;
    }

    public void setOr(List<Group> or) {
        this.or = or;
    }

    public String getSelectProperties() {
        return selectProperties;
    }

    public void setSelectProperties(String selectProperties) {
        this.selectProperties = selectProperties;
    }

    public LinkedHashMap<String, String> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(LinkedHashMap<String, String> orderBy) {
        this.orderBy = orderBy;
    }

    public void addOrderBy(LinkedHashMap<String, String> orderBy) {
        if (this.getOrderBy() == null) {
            this.setOrderBy(new LinkedHashMap<String, String>());
        }
        this.getOrderBy().putAll(orderBy);
    }

    public QueryParams toQueryParams(Class clazz) {
        QueryParams queryParams = new QueryParams(clazz);
        if (NullUtil.isNotEmpty(this.getSelectProperties())) {
            String[] properties = this.getSelectProperties().split(",");
            queryParams.selectProperties(properties);
        }
        if (this.getOrderBy() != null) {
            for (Map.Entry<String, String> entry : orderBy.entrySet()) {
                queryParams.orderBy(entry.getKey()).orderCondition(entry.getValue());
            }
        }
        if (this.getOr() != null) {
            for (Group or : this.getOr()) {
                or.appendParamGroup(queryParams);
            }
        }

        return queryParams;
    }

}
