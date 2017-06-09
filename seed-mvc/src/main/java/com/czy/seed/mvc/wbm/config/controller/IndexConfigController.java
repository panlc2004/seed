package com.czy.seed.mvc.wbm.config.controller;


import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mvc.wbm.config.service.IndexConfigService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@RestController
@RequestMapping("/cfg/indexConfig")
public class IndexConfigController {

    @Resource
    private IndexConfigService indexConfigServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Integer add(IndexConfig indexConfig) {
        int result = indexConfigServiceImpl.insert(indexConfig);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }

    }

    @RequestMapping("/addList")
    public Res addList(@RequestBody List<IndexConfig> indexConfigList) {
        indexConfigServiceImpl.insertList(indexConfigList);
        return Res.ok();
    }

    @RequestMapping("/update")
    public Integer update(IndexConfig indexConfig) {
        int result = indexConfigServiceImpl.updateByPrimaryKey(indexConfig);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/view")
    public IndexConfig view(IndexConfig indexConfig) {
        IndexConfig config = indexConfigServiceImpl.selectRelativeByPrimaryKey(indexConfig.getId());
        if (config != null)
            config.setFlightTypeConfig(configService.selectByPrimaryKey(config.getFlightTypeConfigId()));
        return config;
    }

    @RequestMapping("/list")
    public List<IndexConfig> list(IndexConfig indexConfig) {
        QueryParams queryParams = new QueryParams(IndexConfig.class);

        Integer types = indexConfig.getTypes();
        String houseName = indexConfig.getHouseName();
        QueryParams.Criteria criteria = queryParams.createCriteria();
        if (types != null && types != 0) {
            criteria.andEqualTo("types", types);
        }
        if (houseName != null && !"".equals(houseName.trim())) {
            criteria.andEqualTo("houseName", houseName);
        }

        List<IndexConfig> list = indexConfigServiceImpl.selectListByParams(queryParams);
        return list;
    }

    @RequestMapping("/del")
    public int del(IndexConfig indexConfig) {
        int result = indexConfigServiceImpl.deleteByPrimaryKey(indexConfig.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
