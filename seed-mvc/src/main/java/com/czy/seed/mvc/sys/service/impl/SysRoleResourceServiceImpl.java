package com.czy.seed.mvc.sys.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mvc.sys.entity.SysRoleResource;
import com.czy.seed.mvc.sys.mapper.SysRoleResourceMapper;
import com.czy.seed.mvc.sys.model.Resource;
import com.czy.seed.mvc.sys.model.Resources;
import com.czy.seed.mvc.sys.service.SysResourceService;
import com.czy.seed.mvc.sys.service.SysRoleResourceService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResource> implements SysRoleResourceService {

    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public void saveRoleResources(List<SysRoleResource> roleResourceList) {
        QueryParams queryParams = new QueryParams(SysRoleResource.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysRoleId", roleResourceList.get(0).getSysRoleId());
        super.deleteByParams(queryParams);
        super.insertList(roleResourceList);
    }

    @Override
    public Resources selectResources(long roleId) {
        Resources resources = new Resources();  //定义一个资源对象
        resources.setResourceData(getResourceData());//获取所有资源
        resources.setCheckIds(getCheckIds(roleId));
        resources.setParentIds(resources.getCheckIds());
        return resources;
    }
    /**
     * 角色具有的资源
     *
     * @param checkIDs
     * @return
     */
    private List<Long> getCheckIds(long sysRoleId) {
        QueryParams params = new QueryParams(SysRoleResource.class);
        QueryParams.Criteria paramsCriteria = params.createCriteria();
        paramsCriteria.andEqualTo("sysRoleId", sysRoleId);
        List<SysRoleResource> childId = sysRoleResourceMapper.selectListByParams(params);
        List<Long> result = new ArrayList<>();
        for (SysRoleResource sysRoleResource : childId) {
            result.add(sysRoleResource.getSysResourceId());
        }
        return result;
    }

    /**
     * @return
     */
    private List<Resource> getResourceData() {
        return selectResourceByParentId(0);
    }

    /**
     * 根据父节点ID查询子节点数据
     *
     * @param parentId
     * @return
     */
    private List<Resource> selectResourceByParentId(long parentId) {
        QueryParams params = new QueryParams(SysResource.class);
        QueryParams.Criteria paramsCriteria = params.createCriteria();
        paramsCriteria.andEqualTo("parentId", parentId);
        List<SysResource> children = sysResourceService.selectListByParams(params);
        List<Resource> result = new ArrayList<>();
        for (SysResource sysResource : children) {
            Resource resource = new Resource();
            resource.setId(sysResource.getId());
            resource.setLabel(sysResource.getName());
            List<Resource> resources = selectResourceByParentId(sysResource.getId());
            resource.setChildren(resources);
            result.add(resource);
        }
        return result;
    }

    private Resource convertSysResourceToResource(SysResource sysResource) {
        Resource resource = new Resource();
        resource.setLabel(sysResource.getName());
        resource.setId(sysResource.getId());
        return resource;
    }
}
