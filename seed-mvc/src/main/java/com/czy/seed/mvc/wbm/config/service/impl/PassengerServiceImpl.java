package com.czy.seed.mvc.wbm.config.service.impl;


import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.config.entity.type.Passenger;
import com.czy.seed.mvc.wbm.config.service.PassengerService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@Service
public class PassengerServiceImpl extends BaseServiceImpl<Passenger> implements PassengerService {

    @Override
    @Transactional("tm-default")
    public void saveAsList(List<Passenger> passengerList) {
        if (passengerList != null) {
            QueryParams queryParams = new QueryParams(Crew.class);
            QueryParams.Criteria criteria = queryParams.createCriteria();
            List<Long> list = new ArrayList<>(passengerList.size() + 1);
            list.add(-1L);
            for (Passenger Passenger : passengerList) {
                list.add(Passenger.getId());
            }
            criteria.andIn("id", list);
            if (list.size() > 1) {
                getMapper().deleteByParams(queryParams);
            }
            getMapper().insertList(passengerList);
        }
    }
}
