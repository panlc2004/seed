package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysUserRole;

public interface SysUserRoleService extends BaseService<SysUserRole> {

    /**
     * 保存角色
     * @param userId 用户id
     * @param roleIds 要保存的角色id数组
     * @return
     */
    void saveUserRoles(long userId, Long[] roleIds);
}
