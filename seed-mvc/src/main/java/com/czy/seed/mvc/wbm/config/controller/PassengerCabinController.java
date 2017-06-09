package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.AboutIndexConfigBean;
import com.czy.seed.mvc.wbm.config.entity.type.PassengerCabin;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mvc.wbm.config.service.PassengerCabinService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/19.
 */
@RestController
@RequestMapping("/cfg/passengerCabin")
public class PassengerCabinController {

    @Resource
    private PassengerCabinService passengerCabinServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Integer add(PassengerCabin passengerCabin) {
        int result = passengerCabinServiceImpl.insert(passengerCabin);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }

    }

    @RequestMapping(value = "/addList", method = {RequestMethod.POST})
    public Res addList(@RequestBody List<AboutIndexConfigBean> aboutIndexConfigBeans) {
        passengerCabinServiceImpl.insertAsList(aboutIndexConfigBeans);
        return Res.ok();
    }


    @RequestMapping("/update")
    public Integer update(PassengerCabin passengerCabin) {
        int result = passengerCabinServiceImpl.updateByPrimaryKey(passengerCabin);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/view")
    public PassengerCabin view(PassengerCabin passengerCabin) {
        PassengerCabin cabin = passengerCabinServiceImpl.selectRelativeByPrimaryKey(passengerCabin.getId());
        if (cabin != null)
            cabin.setFlightTypeConfig(configService.selectByPrimaryKey(cabin.getFlightTypeConfigId()));
        return cabin;
    }

    @RequestMapping("/list")
    public List<PassengerCabin> list(PassengerCabin passengerCabin) {
        QueryParams queryParams = new QueryParams(PassengerCabin.class);
        String name = passengerCabin.getName();//客舱名
        QueryParams.Criteria criteria = queryParams.createCriteria();
        if (name != null && !"".equals(name.trim())) {
            criteria.andLike("name", "%" + name + "%");
        }

        List<PassengerCabin> list = passengerCabinServiceImpl.selectListByParams(queryParams);
        return list;
    }

    @RequestMapping("/queryList")
    public Res queryList(@RequestParam Long flightTypeConfigId) {
        List<AboutIndexConfigBean> aboutIndexConfigBeans = passengerCabinServiceImpl.queryList(flightTypeConfigId);
        return Res.ok(aboutIndexConfigBeans);
    }


    @RequestMapping("/del")
    public int del(PassengerCabin passengerCabin) {
        int result = passengerCabinServiceImpl.deleteByPrimaryKey(passengerCabin.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
