package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysRoleResource;
import com.czy.seed.mvc.sys.entity.SysUserRole;

import java.util.List;

public interface SysAccountRoleService extends BaseService<SysUserRole> {
    void saveUserRole(List<SysUserRole> userRoleList);
}
