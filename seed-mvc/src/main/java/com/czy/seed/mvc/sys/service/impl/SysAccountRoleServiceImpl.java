package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysUserRole;
import com.czy.seed.mvc.sys.service.SysAccountRoleService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysAccountRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysAccountRoleService {


    @Override
    public void saveUserRole(long userId, List<Long> roleIds) {
        //删除所有已有角色
        QueryParams queryParams = new QueryParams(SysUserRole.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysUserId", userId);
        super.deleteByParams(queryParams);
        //增加新角色
        if (roleIds != null && roleIds.size() > 0) {
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            for (Long roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setSysUserId(userId);
                sysUserRole.setSysRoleId(roleId);
                sysUserRoleList.add(sysUserRole);
            }
            super.insertList(sysUserRoleList);
        }

    }
}
