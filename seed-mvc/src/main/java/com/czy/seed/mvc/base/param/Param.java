package com.czy.seed.mvc.base.param;

import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.tool.NullUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public void addOr(Group or) {
        if (this.getOr() != null) {
            this.getOr().add(or);
        } else {
            List<Group> temp = new ArrayList<>(1);
            temp.add(or);
            this.setOr(temp);
        }
    }

    public void addOr(List<Group> or) {
        if (this.getOr() != null) {
            this.getOr().addAll(or);
        } else {
            this.setOr(or);
        }
    }

    public String getSelectProperties() {
        return selectProperties;
    }

    public void setSelectProperties(String selectProperties) {
        this.selectProperties = selectProperties;
    }
    public void addSelectProperties(String selectProperties) {
        if (this.getSelectProperties() != null) {
            String s = this.getSelectProperties() + "," + selectProperties;
            this.setSelectProperties(s);
        } else {
            this.setSelectProperties(selectProperties);
        }
    }

    public LinkedHashMap<String, String> getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(LinkedHashMap<String, String> orderBy) {
        this.orderBy = orderBy;
    }

    public void addOrderBy(LinkedHashMap<String, String> orderBy) {
        if (this.getOrderBy() != null) {
            this.getOrderBy().putAll(orderBy);
        } else {
            this.setOrderBy(new LinkedHashMap<String, String>());
        }
    }

    public Group or() {
        Group or = new Group();
        this.addOr(or);
        return or;
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
