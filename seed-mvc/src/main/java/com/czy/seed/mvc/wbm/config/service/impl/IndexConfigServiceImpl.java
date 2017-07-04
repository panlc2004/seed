package com.czy.seed.mvc.wbm.config.service.impl;


import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.entity.type.GalleyGoods;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.config.service.IndexConfigService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panlc on 2017-05-04.
 */
@Service
public class IndexConfigServiceImpl extends BaseServiceImpl<IndexConfig> implements IndexConfigService {

    @Override
    public List<IndexConfig> findIndexConfigList(String flightType, int types) {
        QueryParams queryParams = new QueryParams(IndexConfig.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("flightType", flightType);
        criteria.andEqualTo("types", types);
        return this.selectListByParams(queryParams);
    }

    @Override
    public List<IndexConfig> findIndexConfigList(Long flightTypeConfigId, int types) {
        QueryParams queryParams = new QueryParams(IndexConfig.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("flightTypeConfigId", flightTypeConfigId);
        criteria.andEqualTo("types", types);
        return this.selectListRelativeByParams(queryParams);
    }

    @Override
    public List<IndexConfig> findIndexConfigList(Long flightTypeConfigId, int types, Long aircraftCabinId) {
        QueryParams queryParams = new QueryParams(IndexConfig.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("flightTypeConfigId", flightTypeConfigId);
        criteria.andEqualTo("types", types);
        criteria.andEqualTo("aircraftCabinId", aircraftCabinId);
        return this.selectListRelativeByParams(queryParams);
    }

    @Override
    @Transactional("tm-default")
    public void saveAsList(List<IndexConfig> indexConfigList) {
        if (indexConfigList != null) {
            QueryParams queryParams = new QueryParams(Crew.class);
            QueryParams.Criteria criteria = queryParams.createCriteria();
            List<Long> list = new ArrayList<>(indexConfigList.size() + 1);
            list.add(-1L);
            for (IndexConfig indexConfig : indexConfigList) {
                list.add(indexConfig.getId());
                indexConfig.setTypes(3);
                indexConfig.setAircraftCabinId(-1L);
            }
            criteria.andIn("id", list);
            getMapper().deleteByParams(queryParams);
            getMapper().insertList(indexConfigList);
        }
    }

}
