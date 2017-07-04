package com.czy.seed.mvc.wbm.config.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.service.CrewService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@Service
public class CrewServiceImpl extends BaseServiceImpl<Crew> implements CrewService {

    @Override
    @Transactional("tm-default")
    public void saveAsList(List<Crew> crewList) {

        if (crewList != null) {
            QueryParams queryParams = new QueryParams(Crew.class);
            QueryParams.Criteria criteria = queryParams.createCriteria();
            List<Long> list = new ArrayList<>(crewList.size() + 1);
            list.add(-1L);
            for (Crew crew : crewList) {
                list.add(crew.getId());
            }
            criteria.andIn("id", list);
            getMapper().deleteByParams(queryParams);
            getMapper().insertList(crewList);
        }

    }
}
