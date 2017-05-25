package com.czy.seed.mvc.sys.entity;

import java.io.Serializable;

/**
 * 用户、角色关联类
 * Created by panlc on 2017-05-23.
 */
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 4511028982475620265L;

    private Long id;
    private Long sysUserId;
    private Long sysRoleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
