package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.Passenger;
import com.czy.seed.mvc.wbm.config.entity.type.PassengerCabin;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mvc.wbm.config.service.PassengerService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@RestController
@RequestMapping("/cfg/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Integer add(Passenger passenger) {
        int result = passengerServiceImpl.insert(passenger);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }

    }

    @RequestMapping(value = "/addList", method = {RequestMethod.POST})
    public Res addList(@RequestBody List<Passenger> passengers) {
        int result = passengerServiceImpl.insertList(passengers);
        return Res.ok(result);
    }


    @RequestMapping("/update")
    public Integer update(Passenger passenger) {
        int result = passengerServiceImpl.updateByPrimaryKey(passenger);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/view")
    public Passenger view(Passenger passenger) {
        Passenger passenger1 = passengerServiceImpl.selectRelativeByPrimaryKey(passenger.getId());
        if (passenger1 != null)
            passenger1.setFlightTypeConfig(configService.selectByPrimaryKey(passenger1.getFlightTypeConfigId()));
        return passenger1;

    }

    @RequestMapping("/list")
    public List<Passenger> list(Passenger passenger) {
        QueryParams queryParams = new QueryParams(Passenger.class);
        String passengerTypeCode = passenger.getPassengerTypeCode();
        String nationalityCode = passenger.getNationalityCode();

        QueryParams.Criteria criteria = queryParams.createCriteria();
        if (passengerTypeCode != null && !"".equals(passengerTypeCode.trim())) {
            criteria.andEqualTo("passengerTypeCode", passengerTypeCode);
        }
        if (nationalityCode != null && !"".equals(nationalityCode.trim())) {
            criteria.andEqualTo("nationalityCode", nationalityCode);
        }

        return passengerServiceImpl.selectListByParams(queryParams);
    }

    @RequestMapping("/del")
    public int del(Passenger passenger) {
        int result = passengerServiceImpl.deleteByPrimaryKey(passenger.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
