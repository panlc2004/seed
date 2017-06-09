package com.czy.seed.mvc.wbm.config.controller;

import com.czy.seed.mvc.wbm.config.entity.type.UnionCargo;
import com.czy.seed.mvc.wbm.config.service.UnionCargoService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@RestController
@RequestMapping("/cfg/unionCargo")
public class UnionCargoController {

    @Resource
    private UnionCargoService unionCargoServiceImpl;


    @RequestMapping("/add")
    public Integer add(UnionCargo unionCargo) {
        int result = unionCargoServiceImpl.insert(unionCargo);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }

    }

    @RequestMapping("/update")
    public Integer update(UnionCargo unionCargo) {
        int result = unionCargoServiceImpl.updateByPrimaryKey(unionCargo);
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }

    @RequestMapping("/list")
    public List<UnionCargo> list(UnionCargo unionCargo) {
        QueryParams queryParams = new QueryParams(UnionCargo.class);
        String unionCargoCode = unionCargo.getUnionCargoCode();

        QueryParams.Criteria criteria = queryParams.createCriteria();
        if (unionCargoCode != null && !"".equals(unionCargoCode.trim())) {
            criteria.andLike("unionCargoCode", "%"+unionCargoCode+"%");
        }

        List<UnionCargo> list = unionCargoServiceImpl.selectListByParams(queryParams);
        return list;
    }

    @RequestMapping("/del")
    public int del(UnionCargo unionCargo) {
        int result = unionCargoServiceImpl.deleteByPrimaryKey(unionCargo.getId());
        if (result > 0) {
            return 200;
        } else {
            return 500;
        }
    }
}
