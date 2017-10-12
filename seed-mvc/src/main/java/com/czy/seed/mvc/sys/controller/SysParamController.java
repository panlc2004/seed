package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.service.SysParamService;
import com.czy.seed.mvc.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("sys/param")
@RestController
public class SysParamController extends BaseController<SysParam> {

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping("/updateActive")
    public Res updateActive(@RequestBody SysParam sysParam) {
        sysParamService.updateActive(sysParam);
        return Res.ok(sysParam);
    }

}
