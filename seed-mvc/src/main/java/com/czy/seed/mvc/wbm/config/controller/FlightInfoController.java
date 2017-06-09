package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightConfig;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.service.FlightInfoService;
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
@RequestMapping("/cfg/flightInfo")
public class FlightInfoController {

    @Resource
    private FlightInfoService flightInfoServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Integer add(@RequestBody FlightInfo flightInfo) {
        int result = flightInfoServiceImpl.insert(flightInfo);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }

    }


    @RequestMapping("/view")
    public FlightInfo view(FlightInfo flightInfo) {
        FlightInfo info = flightInfoServiceImpl.selectRelativeByPrimaryKey(flightInfo.getId());
        if (info != null)
            info.setFlightTypeConfig(configService.selectByPrimaryKey(info.getFlightConfigId()));
        return info;
    }

    @RequestMapping("/searchByNo")
    public FlightInfo searchByNo(FlightInfo flightInfo) {
        QueryParams queryParams = new QueryParams(FlightInfo.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("flightNo",flightInfo.getFlightNo());
        FlightInfo info = flightInfoServiceImpl.selectOneRelativeByParams(queryParams);
        if (info != null)
            info.setFlightTypeConfig(configService.selectRelativeByPrimaryKey(info.getFlightConfig().getFlightTypeConfigId()));
        return info;
    }


    @RequestMapping("/update")
    public Integer update(FlightInfo flightInfo) {
        int result = flightInfoServiceImpl.updateByPrimaryKey(flightInfo);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/list")
    public List<FlightInfo> list(FlightInfo flightInfo) {
        QueryParams queryParams = new QueryParams(FlightInfo.class);
        String segment = flightInfo.getSegment();//航段
        String flightNo = flightInfo.getFlightNo();//航班号
        //String flightType = flightInfo.getFlightType();//航班机型
        QueryParams.Criteria criteria = queryParams.createCriteria();
        if (segment != null && !"".equals(segment.trim())) {
            criteria.andLike("segment", "%" + segment + "%");
        }
        if (flightNo != null && !"".equals(flightNo.trim())) {

            criteria.andLike("flightNo", "%" + flightNo + "%");
        }
//        if (flightType != null && !"".equals(flightType.trim())) {
//
//            criteria.andLike("flightType", "%" + flightType + "%");
//        }
        List<FlightInfo> list = flightInfoServiceImpl.selectListByParams(queryParams);
        return list;
    }

    @RequestMapping("/del")
    public int del(FlightConfig flightConfig) {
        int result = flightInfoServiceImpl.deleteByPrimaryKey(flightConfig.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
