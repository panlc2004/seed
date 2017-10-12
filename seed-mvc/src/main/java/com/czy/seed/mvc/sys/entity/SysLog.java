package com.czy.seed.mvc.sys.entity;


import com.czy.seed.mvc.base.entity.IPrepare;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "SEED_SYS_LOG")
public class SysLog implements IPrepare {

    private static final long serialVersionUID = -5605619995509261468L;
    @Id
    private Long id; //主键
    private Long createBy;//对应SysRole 中的id
    private Date createDt;
    private String operation;
    private String method;
    private String params;
    private String ip;

    @Transient
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
