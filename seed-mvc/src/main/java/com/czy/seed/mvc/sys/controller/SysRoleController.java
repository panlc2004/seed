package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.base.entity.EntityUtil;
import com.czy.seed.mvc.sys.entity.*;
import com.czy.seed.mvc.sys.service.SysRoleService;
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

@RequestMapping("/sys/role")
@RestController
public class SysRoleController extends BaseController<SysRole> {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 根据ID删除数据
     * @param id
     * @return Res
     */
    @RequestMapping("/deleteByPrimary/{id}")
    public Res deleteByPrimary(@PathVariable("id") long id) {
//        QueryParams params = new QueryParams(SysRole.class);
//        QueryParams.Criteria criteria = params.createCriteria();
//        criteria.andEqualTo("id", id);
        sysRoleService.deleteByPrimaryKey(id);
        return Res.ok();
    }

}
