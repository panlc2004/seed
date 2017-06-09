package com.czy.seed.mvc.wbm.config.controller;


import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.AboutIndexConfigBean;
import com.czy.seed.mvc.wbm.config.entity.type.CargoHold;
import com.czy.seed.mvc.wbm.config.entity.type.PassengerCabin;
import com.czy.seed.mvc.wbm.config.service.CargoHoldService;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
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
@RequestMapping("/cfg/cargoHold")
public class CargoHoldController {

    @Resource
    private CargoHoldService cargoHoldServiceImpl;

    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Integer add(CargoHold cargoHold) {
        int result = cargoHoldServiceImpl.insert(cargoHold);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }

    }

    @RequestMapping(value = "/addList", method = {RequestMethod.POST})
    public Res addList(@RequestBody List<AboutIndexConfigBean> aboutIndexConfigBeans) {
        cargoHoldServiceImpl.insertAsList(aboutIndexConfigBeans);
        return Res.ok();
    }


    @RequestMapping("/view")
    public CargoHold view(CargoHold cargoHold) {
        CargoHold hold = cargoHoldServiceImpl.selectRelativeByPrimaryKey(cargoHold.getId());
        if (hold != null)
            hold.setFlightTypeConfig(configService.selectByPrimaryKey(hold.getFlightTypeConfigId()));
        return hold;
    }


    @RequestMapping("/update")
    public Integer update(CargoHold cargoHold) {
        int result = cargoHoldServiceImpl.updateByPrimaryKey(cargoHold);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/list")
    public List<CargoHold> list(CargoHold cargoHold) {
        QueryParams queryParams = new QueryParams(CargoHold.class);
        String typeName = cargoHold.getCargoTypeName();
        String code = cargoHold.getCode();
        String name = cargoHold.getName();
        QueryParams.Criteria criteria = queryParams.createCriteria();
        if (typeName != null && !"".equals(typeName.trim())) {
            criteria.andEqualTo("cargoTypeName", typeName);
        }

        if (code != null && !"".equals(code.trim())) {
            criteria.andLike("code", "%" + code + "%");
        }
        if (name != null && !"".equals(name.trim())) {
            criteria.andLike("name", "%" + name + "%");
        }

        List<CargoHold> list = cargoHoldServiceImpl.selectListByParams(queryParams);
        return list;
    }

    @RequestMapping("/del")
    public int del(CargoHold cargoHold) {
        int result = cargoHoldServiceImpl.deleteByPrimaryKey(cargoHold.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
