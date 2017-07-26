package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysRoleResource;
import com.czy.seed.mvc.sys.service.SysRoleResourceService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by PLC on 2017/5/29.
 */
@Service
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResource> implements SysRoleResourceService {
    /**
     * 添加用户权限
     *
     * @param roleResourceList
     */
    @Override
    @Transactional(transactionManager = "tm-default")
    public void saveRoleResources(Long roleId, List<SysRoleResource> roleResourceList) {
        QueryParams queryParams = new QueryParams(SysRoleResource.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysRoleId", roleId);
        super.deleteByParams(queryParams);
        super.insertList(roleResourceList);
    }

}
