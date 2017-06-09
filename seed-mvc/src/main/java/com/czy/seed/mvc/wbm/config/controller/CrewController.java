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
 * Created by Administrator on 2017/5/19.
 */
@RestController
@RequestMapping("/cfg/crew")
public class CrewController {

    @Resource
    private CrewService crewServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Integer add(Crew crew) {
        int result = crewServiceImpl.insert(crew);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }

    }

    @RequestMapping("/addList")
    public Res addList(@RequestBody List<Crew> crews) {
        crewServiceImpl.insertList(crews);
        return Res.ok();
    }

    @RequestMapping("/update")
    public Integer update(Crew crew) {
        int result = crewServiceImpl.updateByPrimaryKey(crew);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/view")
    public Crew view(Crew crew) {
        Crew crew1 = crewServiceImpl.selectRelativeByPrimaryKey(crew.getId());
        if (crew1 != null)
            crew1.setFlightTypeConfig(configService.selectByPrimaryKey(crew1.getFlightTypeConfigId()));
        return crew1;
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

    @RequestMapping("/del")
    public int del(Crew crew) {
        int result = crewServiceImpl.deleteByPrimaryKey(crew.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
