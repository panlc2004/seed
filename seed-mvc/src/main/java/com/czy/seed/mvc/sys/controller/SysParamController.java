package com.czy.seed.mvc.sys.controller;

import com.czy.seed.mvc.base.controller.BaseController;
import com.czy.seed.mvc.sys.entity.SysParam;
import com.czy.seed.mvc.sys.entity.SysResource;
import com.czy.seed.mvc.sys.service.SysParamService;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.base.QueryParams;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("sys/param")
@RestController
public class SysParamController extends BaseController<SysParam> {

    @Autowired
    private SysParamService sysParamService;


    /**
     * 激活系统设置
     * @param id
     * @param active
     * @return
     */
    @RequestMapping("updateActiveByPrimaryKey/{id}/{active}")
    public Res updateActiveByPrimaryKey(@PathVariable long id,@PathVariable int active){
        SysParam sysParam=new SysParam();
        if(active==1)
        {
            sysParam.setActive(0);
        }
        else{
            sysParam.setActive(1);
        }
        sysParam.setId(id);
//        QueryParams queryParams = new QueryParams(SysParam.class);
//        QueryParams.Criteria criteria = queryParams.createCriteria();
//        criteria.andEqualTo("id", id);

            sysParamService.updateActiveByPrimaryKey(sysParam);
//        sysParamService.updateSelectiveByParams(sysParam, queryParams);
        return Res.ok(sysParam);
    }
}
