package com.czy.seed.mvc.sys.entity;

import com.czy.seed.mvc.base.entity.IPrepare;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "SEED_SYS_ROLE")
public class SysRole implements IPrepare {

    private static final long serialVersionUID = 4659702439449421452L;

    private Long id;
    private Date createDt;
    private Long createBy;
    private Date updateDt;
    private Long updateBy;
    private Long sysOrgId;
    private String code;
    private String name;
    private String memo;

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

    public Long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(Long sysOrgId) {
        this.sysOrgId = sysOrgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                super.toString() + '\'' +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
