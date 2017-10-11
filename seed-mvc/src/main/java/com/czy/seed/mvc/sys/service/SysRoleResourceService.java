package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysRoleResource;
import com.czy.seed.mvc.sys.model.Resources;

import java.util.List;

public interface SysRoleResourceService extends BaseService<SysRoleResource> {
    void saveRoleResources(List<SysRoleResource> roleResourceList);

    /**
     *根据角色id查询资源数据
     * @param roleId
     * @return
     */
    Resources selectResources(long roleId);
}
