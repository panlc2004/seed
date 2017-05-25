package com.czy.seed.mvc.sys.entity;

import java.io.Serializable;

/**
 * 角色资源
 * Created by PLC on 2017/5/23.
 */
public class SysRoleResource implements Serializable {
    private static final long serialVersionUID = -3755254892758648117L;
    private Long id;
    private Long sysRoleId;
    private Long sysResourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long getSysResourceId() {
        return sysResourceId;
    }

    public void setSysResourceId(Long sysResourceId) {
        this.sysResourceId = sysResourceId;
    }
}
