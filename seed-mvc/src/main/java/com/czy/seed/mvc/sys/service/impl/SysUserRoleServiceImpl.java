package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysUserRole;
import com.czy.seed.mvc.sys.service.SysUserRoleService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

    @Transactional
    public void saveUserRoles(long userId, Long[] roleIds) {
        QueryParams sysUserRoleParams = new QueryParams(SysUserRole.class);
        QueryParams.Criteria criteria = sysUserRoleParams.createCriteria();
        criteria.andEqualTo("sysUserId", userId);
        deleteByParams(sysUserRoleParams);
        List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
        for (Long roleId : roleIds) {
            SysUserRole sur = new SysUserRole();
            sur.setSysUserId(userId);
            sur.setSysRoleId(roleId);
            insert(sur);
        }
    }

}
