package com.czy.seed.mvc.sys.entity;

import com.czy.seed.mvc.base.entity.IPrepare;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "SEED_SYS_USER_ROLE")
public class SysUserRole implements IPrepare {

    private static final long serialVersionUID = 4511028982475620265L;

    private Long id;
    private Date createDt;
    private Long createBy;
    private Long sysOrgId;
    private Long sysUserId;
    private Long sysRoleId;

    @One2One(columns = "sysRoleId = id")
    private SysRole sysRole;

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

    public Long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(Long sysOrgId) {
        this.sysOrgId = sysOrgId;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }
}
