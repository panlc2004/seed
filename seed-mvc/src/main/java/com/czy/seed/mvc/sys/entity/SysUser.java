package com.czy.seed.mvc.sys.entity;

import com.czy.seed.mvc.base.entity.IPrepare;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "SEED_SYS_USER")
public class SysUser implements IPrepare {

    private static final long serialVersionUID = 6022588492678547207L;

    @Id
    private Long id;
    private Long sysOrgId;
    private Long sysDeptId;
    private String name;
    private String email;

    @Transient
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(Long sysOrgId) {
        this.sysOrgId = sysOrgId;
    }

    public Long getSysDeptId() {
        return sysDeptId;
    }

    public void setSysDeptId(Long sysDeptId) {
        this.sysDeptId = sysDeptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
