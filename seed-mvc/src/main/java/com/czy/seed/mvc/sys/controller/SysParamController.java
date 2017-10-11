package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysDept;
import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.service.SysParamService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.tool.NullUtil;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("sys/param")
@RestController
public class SysParamController extends BaseController<SysParam> {
    @Autowired
    SysParamService sysParamService;

    /**
     * 该功能用于激活用户
     * @param id 主键
     * @param active 是否激活
     * @return Res
     */
    @RequestMapping("/updateActiveByPrimaryKey/{id}/{active}")
    public Res updateActiveByPrimaryKey(@PathVariable long id, @PathVariable long active) {
        sysParamService.updateActiveByPrimaryKey(id, active);
        return Res.ok();
    }


}
