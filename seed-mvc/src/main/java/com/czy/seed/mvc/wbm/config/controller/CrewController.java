/*
 * 文 件 名 : CrewController.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <乘务信息控制器>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017年6月10日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.controller;


import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.service.CrewService;
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
 * @Description: <乘务信息配置控制器>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
 */
@RestController
@RequestMapping("/cfg/crew")
public class CrewController {

    @Resource
    private CrewService crewServiceImpl;

    @Resource
    private FlightTypeConfigService configService;


    /**
     * 批量添加乘务信息
     *
     * @param crews 乘务新信息配置
     * @return
     */
    @RequestMapping("/addList")
    public Res addList(@RequestBody List<Crew> crews) {
        crewServiceImpl.saveAsList(crews);
        return Res.ok();
    }


    @RequestMapping("/list")
    public List<Crew> list(Crew crew) {
        QueryParams queryParams = new QueryParams(Crew.class);

        String differentCountries = crew.getDifferentCountries();
        String positionCode = crew.getPositionCode();
        QueryParams.Criteria criteria = queryParams.createCriteria();
        if (differentCountries != null && !"".equals(differentCountries.trim())) {
            criteria.andEqualTo("differentCountries", differentCountries);
        }
        if (positionCode != null && !"".equals(positionCode.trim())) {
            criteria.andEqualTo("positionCode", positionCode);
        }

        List<Crew> list = crewServiceImpl.selectListByParams(queryParams);
        return list;
    }

}
