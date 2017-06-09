package com.czy.seed.mvc.wbm.config.controller;


import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightConfig;
import com.czy.seed.mvc.wbm.config.service.FlightConfigService;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
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
@RequestMapping("/cfg/flightCfg")
public class FlightConfigController {

    @Resource
    private FlightConfigService flightConfigServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Res add(@RequestBody FlightConfig flightConfig) {
        flightConfigServiceImpl.insert(flightConfig);
        return Res.ok(flightConfig);
    }

    @RequestMapping("/view")
    public FlightConfig view(FlightConfig flightConfig) {
        FlightConfig config = flightConfigServiceImpl.selectRelativeByPrimaryKey(flightConfig.getId());
        if (config != null)
            config.setFlightTypeConfig(configService.selectByPrimaryKey(config.getFlightTypeConfigId()));
        return config;
    }

    @RequestMapping("/update")
    public Integer update(FlightConfig flightConfig) {
        int result = flightConfigServiceImpl.updateByPrimaryKey(flightConfig);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/list")
    public List<FlightConfig> list(FlightConfig flightConfig) {
        QueryParams queryParams = new QueryParams(FlightConfig.class);
        String num = flightConfig.getNum();//出厂序号
        String msn = flightConfig.getMsn();//机号
        QueryParams.Criteria criteria = queryParams.createCriteria();
        if (num != null && !"".equals(num.trim())) {
            criteria.andLike("num", "%" + num + "%");
        }
        if (msn != null && !"".equals(msn.trim())) {

            criteria.andLike("msn", "%" + msn + "%");
        }
        List<FlightConfig> list = flightConfigServiceImpl.selectListByParams(queryParams);
        return list;
    }

    @RequestMapping("/del")
    public int del(FlightConfig flightConfig) {
        int result = flightConfigServiceImpl.deleteByPrimaryKey(flightConfig.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
