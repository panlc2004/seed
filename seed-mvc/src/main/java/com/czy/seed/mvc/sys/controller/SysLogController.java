package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.base.param.Param;
import com.czy.seed.mvc.sys.entity.SysLog;
import com.czy.seed.mvc.sys.model.Log;
import com.czy.seed.mvc.sys.service.SysLogService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys/log")
public class SysLogController extends BaseController<SysLog> {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/selectPageRelative/{pageNum}/{pageSize}")
    public Res selectPageRelative(@PathVariable int pageNum, @PathVariable int pageSize, @RequestBody Param param) {
        QueryParams queryParams = param.toQueryParams(SysLog.class);
        Page<SysLog> page = sysLogService.selectPageRelative(pageNum, pageSize, queryParams);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

    @RequestMapping("selectPageRelativeByParam/{name}/{createDt}/{operation}/{pageNum}/{pageSize}")
    public Res selectPageRelativeByParam(@PathVariable String name,
                                         @PathVariable String createDt,
                                         @PathVariable String operation,
                                         @PathVariable int pageNum, @PathVariable int pageSize) {
        Param param = new Param();
        QueryParams queryParams = param.toQueryParams(Log.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andLike("name", name);
        criteria.andBetween("createDt", createDt.split(",")[0], createDt.split(",")[1]);
        criteria.andLike("operation", operation);
        Page<SysLog> page = sysLogService.selectPageRelativeByParams(pageNum, pageSize, queryParams);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages());
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }
}
