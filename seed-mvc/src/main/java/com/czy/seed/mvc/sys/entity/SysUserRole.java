package com.czy.seed.mvc.sys.entity;

import java.io.Serializable;

/**
 * 用户、角色关联类
 * Created by panlc on 2017-05-23.
 */
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 4511028982475620265L;

    private Long id;
    private Long userId;
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
