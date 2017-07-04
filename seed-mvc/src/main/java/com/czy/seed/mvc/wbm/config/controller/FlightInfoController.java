/*
 * 文 件 名 : FlightInfoController.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <航班总体参数控制器>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017年6月10日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.util.Res;
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
 * <一句话功能简介><br>
 *
 * @author [011424]zhangyang@inner.czy.com
 * @version [版本号, 2017年6月9日]
 * @Description: <航班总体参数配置控制器>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
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

    /**
     * 保存数据:当传入数据有id时，进行修改操作，无id时，进行新增操作
     *
     * @param flightInfo 数据实体
     * @return 新增/修改数据的id值
     */
    @RequestMapping("/save")
    public Res save(@RequestBody FlightInfo flightInfo) {
        if (flightInfo.getId() == null) {
            flightInfoServiceImpl.insert(flightInfo);
        } else {
            flightInfoServiceImpl.updateSelectiveByPrimaryKey(flightInfo);
        }
        return Res.ok(flightInfo);
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
        criteria.andEqualTo("flightNo", flightInfo.getFlightNo());
        FlightInfo info = flightInfoServiceImpl.selectOneRelativeByParams(queryParams);
        if (info != null)
            info.setFlightTypeConfig(configService.selectRelativeByPrimaryKey(info.getFlightConfig().getFlightTypeConfigId()));
        return info;
    }

    @RequestMapping("/searchFlightInfo")
    public Res searchFlightInfo() {
        List<FlightInfo> infoList = flightInfoServiceImpl.selectListRelativeByParams(new QueryParams(FlightInfo.class));
        if (infoList != null) {
            for (FlightInfo flightInfo : infoList) {
                if (flightInfo != null) {
                    flightInfo.setFlightTypeConfig(configService.selectRelativeByPrimaryKey(flightInfo.getFlightConfig().getFlightTypeConfigId()));
                }
            }
        }
        return Res.ok(infoList);
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
