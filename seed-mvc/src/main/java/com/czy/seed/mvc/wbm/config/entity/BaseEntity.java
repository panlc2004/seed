package com.czy.seed.mvc.wbm.config.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by panlc on 2017-04-24.
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2091674550106094691L;

    private int id;
    private int createdBy;
    private Date createdDt;
    private int updatedBy;
    private Date updateDt;
    private int version;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
