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
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.font.FontRenderContext;
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


    /**
     * 查询所有资源
     * @param roleId  角色Id
     * @return  Resourece
     */
    public Resources selectResources(long roleId) {
        Resources resources = new Resources();//所有的资源，用户资源，以及父级资源ID 的存储
        resources.setResourceData(getResourceData());//查询父级资源（id name）以及父级资源下所有子资源
       resources.setCheckIds(getCheckIds(roleId));//查询用户所拥有的资源（id）

       // resources.setParentIds(resources.getCheckIds());
        return resources;
    }

    /**
     * 通过角色Id查询用户已有资源
     * @param sysRoleId 角色Id
     * @return Long 返回资源菜单的ID
     */
    private List<Long> getCheckIds(long sysRoleId) {
            QueryParams queryParams = new QueryParams(SysRoleResource.class);
            QueryParams.Criteria criteria = queryParams.createCriteria();
            criteria.andEqualTo("sysRoleId",sysRoleId);
            List<SysRoleResource> childId = sysRoleResourceMapper.selectListByParams(queryParams);
            List<Long> result = new ArrayList<>();
            for (SysRoleResource sysRoleResource :childId)
            {
                result.add(sysRoleResource.getSysResourceId());
            }
            return  result;
    }

    /**
     * @return   查询父级资源
     */
    private List<Resource> getResourceData() {
        return selectResourceByParentId(0);
    }

    /**
     * 根据父节点ID查询子节点数据
     * @param parentId  父节点ID
     * @return List<Resource>
     */
    private List<Resource> selectResourceByParentId(long parentId) {
        QueryParams params = new QueryParams(SysResource.class);
        QueryParams.Criteria paramsCriteria = params.createCriteria();
        paramsCriteria.andEqualTo("parentId", parentId); //设置查询条件 parentId 开始为0
        List<SysResource> children = sysResourceService.selectListByParams(params);//查询到父级资源
        List<Resource> result = new ArrayList<>();
        for (SysResource sysResource : children) {
            Resource resource = new Resource();
            resource.setId(sysResource.getId());
            resource.setLabel(sysResource.getName());
            //把父级资源的id name 存放进去
            List<Resource> resources = selectResourceByParentId(sysResource.getId());//查找父级下子资源下的资源。。
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
