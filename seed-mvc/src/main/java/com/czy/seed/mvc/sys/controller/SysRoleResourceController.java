package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysRoleResource;
import com.czy.seed.mvc.sys.service.SysRoleResourceService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sys/roleResource")
public class SysRoleResourceController {

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    /**
     * 查询对应的角色
     * @param roleId  角色ID
     * @return Res
     */
    @RequestMapping("/selectResourceForRole/{roleId}")
    public Res selectResourceForRole(@PathVariable long roleId) {
        QueryParams queryParams = new QueryParams(SysRoleResource.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysRoleId", roleId);
        List<SysRoleResource> list = sysRoleResourceService.selectListRelativeByParams(queryParams);
        return Res.ok(list);
    }

    /**
     * 修改信息
     * @param sysRoleResource  实体类
     * @return Res
     */
    @RequestMapping("/save")
    public Res save(@RequestBody SysRoleResource sysRoleResource) {
        if (sysRoleResource.getId() == null) {
            sysRoleResourceService.insert(sysRoleResource);
        } else {
            sysRoleResourceService.updateSelectiveByPrimaryKey(sysRoleResource);
        }
        return Res.ok(sysRoleResource.getId());
    }

    /**
     * 通过主键删除
     * @param id
     * @return Res
     */
    @RequestMapping("/deleteByPrimary/{id}")
    public Res deleteByPrimary(@PathVariable long id) {
        sysRoleResourceService.deleteByPrimaryKey(id);
        return Res.ok();
    }

    /**
     *
     * @param roleResourceList
     * @return
     */
    @RequestMapping("/saveRoleResource")
    public Res saveRoleResource(@RequestBody List<SysRoleResource> roleResourceList) {
        sysRoleResourceService.saveRoleResources(roleResourceList);
        return Res.ok();
    }

    /**
     * 根据角色Id查询资源
     * @param roleId 角色Id
     * @return Res
     */
    @RequestMapping("selectResources/{roleId}")
    public Res selectResources(@PathVariable("roleId") long roleId) {
        return Res.ok(sysRoleResourceService.selectResources(roleId));
    }
}
