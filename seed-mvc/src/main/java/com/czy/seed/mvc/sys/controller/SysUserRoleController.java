package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysUserRole;
import com.czy.seed.mvc.sys.service.SysAccountRoleService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/userRole")
public class SysUserRoleController {

    @Autowired
    private SysAccountRoleService sysAccountRoleService;


    @RequestMapping("/selectList")
    public Res selectList() {
        QueryParams queryParams = new QueryParams(SysUserRole.class);
        List<SysUserRole> sysUserRoles = sysAccountRoleService.selectListByParams(queryParams);
        return Res.ok(sysUserRoles);
    }

    @RequestMapping("/selectListForUser/{userId}")
    public Res selectListForUser(@PathVariable long userId) {
        QueryParams queryParams = new QueryParams(SysUserRole.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("sysUserId", userId);
        List<SysUserRole> sysUserRoles = sysAccountRoleService.selectListRelativeByParams(queryParams);
        return Res.ok(sysUserRoles);
    }

    @RequestMapping("/saveUserRole")
    public Res saveUserRole(@RequestParam long userId, @RequestParam(value = "roleIds[]", required = false) List<Long> roleIds) {
        sysAccountRoleService.saveUserRole(userId, roleIds);
        return Res.ok();
    }


}
