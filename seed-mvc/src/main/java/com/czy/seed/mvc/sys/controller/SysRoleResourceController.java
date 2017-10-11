package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mvc.sys.entity.SysRoleResource;
import com.czy.seed.mvc.sys.service.SysResourceService;
import com.czy.seed.mvc.sys.service.SysRoleResourceService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/sys/roleResource")
@RestController
public class SysRoleResourceController extends BaseController<SysRoleResource> {

    @Autowired
    private SysRoleResourceService sysRoleResourceService;
    @Autowired
    private SysResourceService sysResourceService;

    @RequestMapping("selectResources/{roleId}")
    public Res selectResources(@PathVariable("roleId") long roleId) {
        return Res.ok(sysRoleResourceService.selectResources(roleId));
    }

    @RequestMapping("/selectResourceForRole/{roleId}")
    public Res selectResourceForRole(@PathVariable long roleId) {
        QueryParams queryParams = new QueryParams(SysRoleResource.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysRoleId", roleId);
        List<SysRoleResource> list = sysRoleResourceService.selectListRelativeByParams(queryParams);
        return Res.ok(list);
    }

    @RequestMapping("/save")
    public Res save(@RequestBody SysRoleResource sysRoleResource) {
        if (sysRoleResource.getId() == null) {
            sysRoleResourceService.insert(sysRoleResource);
        } else {
            sysRoleResourceService.updateSelectiveByPrimaryKey(sysRoleResource);
        }
        return Res.ok(sysRoleResource.getId());
    }

    @RequestMapping("/deleteByPrimary/{id}")
    public Res deleteByPrimary(@PathVariable long id) {
        sysRoleResourceService.deleteByPrimaryKey(id);
        return Res.ok();
    }

    @RequestMapping("/saveRoleResource")
    public Res saveRoleResource(@RequestBody List<SysRoleResource> roleResourceList) {
        sysRoleResourceService.saveRoleResources(roleResourceList);
        return Res.ok();
    }

}
