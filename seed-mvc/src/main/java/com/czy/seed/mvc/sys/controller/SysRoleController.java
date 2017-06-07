package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysOrg;
import com.czy.seed.mvc.sys.entity.SysRole;
import com.czy.seed.mvc.sys.service.SysRoleService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.tool.NullUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 003914[panlc] on 2017-06-06.
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/sys/role");
    }

    @RequestMapping("/selectPageByParams")
    public Res selectPageByParams(@RequestParam Map<String, Object> params) {
        Integer pageNum = Integer.parseInt(params.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(params.get("pageSize").toString());
        QueryParams queryParams = new QueryParams(SysOrg.class);
        if (params.containsKey("name") && NullUtil.isNotEmpty(params.get("name"))) {
            QueryParams.Criteria criteria = queryParams.createCriteria();
            criteria.andLike("name", "%" + params.get("name") + "%");
        }
        Page<SysRole> page = sysRoleService.selectPageByParams(pageNum, pageSize, queryParams); Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

    @RequestMapping("/save")
    public Res save(@RequestBody SysRole sysRole) {
        if (sysRole.getId() == null) {
            sysRoleService.insert(sysRole);
        } else {
            sysRoleService.updateByPrimaryKeySelective(sysRole);
        }
        return Res.ok(sysRole.getId());
    }

    @RequestMapping("/deleteByPrimary/{id}")
    public Res deleteByPrimary(@PathVariable long id) {
        sysRoleService.deleteByPrimaryKey(id);
        return Res.ok();
    }

    @RequestMapping("/selectList")
    public Res selectList() {
        QueryParams queryParams = new QueryParams(SysRole.class);
        List<SysRole> sysRoles = sysRoleService.selectListByParams(queryParams);
        return Res.ok(sysRoles);
    }


}
