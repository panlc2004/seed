package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysOrg;
import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.service.SysParamService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PLC on 2017/6/3.
 */
@RequestMapping("sys/param")
@RestController
public class SysParamController {

    @Autowired
    private SysParamService sysParamService;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("sys/param");
    }

    @RequestMapping("/selectPageByParams")
    public Res selectPageByParams(@RequestParam  Map<String, Object> params) {
        Integer pageNum = Integer.parseInt(params.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(params.get("pageSize").toString());
        QueryParams queryParams = new QueryParams(SysOrg.class);
        Page<SysParam> page = sysParamService.selectPageByParams(pageNum, pageSize, queryParams);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages() + 100);
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

}
