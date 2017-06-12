package com.czy.seed.mvc.base.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 003914[panlc] on 2017-06-08.
 */
public class BaseEntity implements Serializable {

    @Id
    private Long id;

    /**
     * 数据创建时间
     */
    private Date createDt;

    /**
     * 数据创建人
     */
    private Long createBy;

    /**
     * 数据修改时间
     */
    private Date updateDt;

    /**
     * 数据修改人
     */
    private Long updateBy;

    public boolean autoGen; //是否自动生成操作人、操作时间、修改时间、修改人、版本号等记录

    /**
     * 乐观锁版本
     */
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isAutoGen() {
        return autoGen;
    }

    public void setAutoGen(boolean autoGen) {
        this.autoGen = autoGen;
    }
}
