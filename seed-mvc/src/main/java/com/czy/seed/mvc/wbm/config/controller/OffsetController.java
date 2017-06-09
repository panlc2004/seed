package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.util.Res;
import com.czy.seed.mvc.wbm.config.entity.type.Offset;
import com.czy.seed.mvc.wbm.config.service.FlightTypeConfigService;
import com.czy.seed.mvc.wbm.config.service.OffsetService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@RestController
@RequestMapping("/cfg/offset")
public class OffsetController {

    @Resource
    private OffsetService offsetServiceImpl;


    @Resource
    private FlightTypeConfigService configService;

    @RequestMapping("/add")
    public Res add(Offset offset) {
        int result = offsetServiceImpl.insert(offset);
        return Res.ok(result);
    }

    @RequestMapping("/update")
    public Integer update(Offset offset) {
        int result = offsetServiceImpl.updateByPrimaryKey(offset);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/view")
    public Offset view(Offset offset) {
        Offset offset1 = offsetServiceImpl.selectRelativeByPrimaryKey(offset.getId());
        if (offset1 != null)
            offset1.setFlightTypeConfig(configService.selectByPrimaryKey(offset1.getFlightTypeConfigId()));
        return offset1;
    }

    @RequestMapping("/list")
    public List<Offset> list(Offset offset) {
        QueryParams queryParams = new QueryParams(Offset.class);
        String type = offset.getType();//类型
        QueryParams.Criteria criteria = queryParams.createCriteria();

        if (type != null && !"".equals(type.trim())) {

            criteria.andLike("type", "%" + type + "%");
        }
        List<Offset> list = offsetServiceImpl.selectListByParams(queryParams);
        return list;
    }

    @RequestMapping("/del")
    public int del(Offset offset) {
        int result = offsetServiceImpl.deleteByPrimaryKey(offset.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
