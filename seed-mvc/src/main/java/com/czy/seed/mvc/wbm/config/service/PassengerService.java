package com.czy.seed.mvc.wbm.config.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.entity.type.Passenger;

import java.util.List;

/**
 * Created by panlc on 2017-05-04.
 */
public interface PassengerService extends BaseService<Passenger> {
    /**
     * 保存和修改乘客信息,在列表集合中修改操作先直接删除列表数据,再新增列表数据
     *
     * @param passengerList 乘客信息列表
     * @return
     */
    void saveAsList(List<Passenger> passengerList);
}
