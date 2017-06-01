package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.sys.entity.SysUser;
import com.czy.seed.mvc.sys.service.SysUserService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 003914[panlc] on 2017-06-01.
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("/selectByPage")
    public Res selectByPage(Integer pageNum, Map<String, Object> params) {
        QueryParams queryParams = new QueryParams(SysUser.class);
        if(pageNum == null) {
            pageNum = 0;
        }
        int pageLimit = 30;
        Page<SysUser> page = sysUserService.selectPageByParams(pageNum, pageLimit, queryParams);
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageNum", page.getPageNum());
        pageInfo.put("total", page.getTotal());
        pageInfo.put("pages", page.getPages() * 10);
        pageInfo.put("page", page);
        return Res.ok(pageInfo);
    }

}
