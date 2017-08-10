package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysRoleResource;
import com.czy.seed.mvc.sys.service.SysRoleResourceService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResource> implements SysRoleResourceService {
    @Override
    public void saveRoleResources(List<SysRoleResource> roleResourceList) {
        QueryParams queryParams = new QueryParams(SysRoleResource.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysRoleId", roleResourceList.get(0).getSysRoleId());
        super.deleteByParams(queryParams);
        super.insertList(roleResourceList);
    }

}
