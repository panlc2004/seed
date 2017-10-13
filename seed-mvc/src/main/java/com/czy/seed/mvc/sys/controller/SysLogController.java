package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.base.param.Param;
import com.czy.seed.mvc.sys.entity.SysLog;
import com.czy.seed.mvc.sys.service.SysLogService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys/log")
public class SysLogController extends BaseController<SysLog> {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/selectExtendPageByParam/{pageNum}/{pageSize}")
    public Res selectExtendPageByParam(@PathVariable int pageNum, @PathVariable int pageSize, @RequestParam Map<String, Object> params) {
        Page<SysLog> page = sysLogService.selectExtendPageByParam(pageNum, pageSize, params);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

}
