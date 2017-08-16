package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.service.SysDeptService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysOrgService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/sys/org");
        return mv;
    }

    @RequestMapping("/selectOrgTree")
    public List<SysDept> selectOrgTree() {
        return sysOrgService.selectOrgTree();
    }

    @RequestMapping("/save")
    public String save(@RequestBody SysDept sysOrg) {
        if (sysOrg.getId() == null) {
            sysOrgService.insert(sysOrg);
        } else {
            sysOrgService.updateSelectiveByPrimaryKey(sysOrg);
        }
        return sysOrg.getId().toString();
    }

    @RequestMapping("/loadData")
    public SysDept loadData(long id) {
        return sysOrgService.selectByPrimaryKey(id);
    }

    @RequestMapping("/deleteOrg")
    public Res deleteOrg(long id) {
        QueryParams params = new QueryParams(SysDept.class);
        QueryParams.Criteria criteria = params.createCriteria();
        criteria.andEqualTo("parentId", id);
        int count = sysOrgService.selectCountByParams(params);
        if (count > 0) {
            return Res.error("当前机构有下属机构，无法删除");
        }
        sysOrgService.deleteByPrimaryKey(id);
        return Res.ok();
    }

}

