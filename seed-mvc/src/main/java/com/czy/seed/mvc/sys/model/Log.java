package com.czy.seed.mvc.sys.model;

import javax.persistence.JoinColumn;
import java.util.Date;
import java.util.List;

public class Log {
    @JoinColumn
    private String name;
    private List<Date> createDt;
    private String operation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Date> getCreateDt() {
        return createDt;
    }

    public void setCreateDt(List<Date> createDt) {
        this.createDt = createDt;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
