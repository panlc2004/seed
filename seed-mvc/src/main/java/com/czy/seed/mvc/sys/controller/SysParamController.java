package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
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
    private SysParamService sysParamService;
    @RequestMapping("/updateActive/{id}")
    public Res updateActive(@PathVariable Long id) {
        sysParamService.updateActive(id);
        return Res.ok(id);
    }
    @RequestMapping("/deleteActive/{id}")
    public Res deleteActive(@PathVariable Long id){
        sysParamService.deleteActive(id);
        return Res.ok(id);
    }


}
