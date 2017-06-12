package com.czy.seed.mvc.sys.entity;


import com.czy.seed.mvc.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by 003914[panlc] on 2017-06-12.
 */
public class SysLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -5605619995509261468L;
    private String operation;
    private String method;
    private String params;
    private String ip;


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
