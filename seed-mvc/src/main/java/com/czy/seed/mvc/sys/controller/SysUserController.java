package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysUser;
import com.czy.seed.mvc.sys.service.SysUserService;
import com.czy.seed.mvc.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUser> {

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/save")
    public String save(@RequestBody SysUser sysUser) {
        if (sysUser.getId() == null) {
            sysUserService.insert(sysUser);
        } else {
            sysUserService.updateSelectiveByPrimaryKey(sysUser);
        }
        return sysUser.getId().toString();
    }

    @RequestMapping("/deleteByPrimary/{id}")
    public Res deleteByPrimary(@PathVariable("id") long id) {
        sysUserService.deleteByPrimaryKey(id);
        return Res.ok();
    }

}
