package com.czy.seed.mvc.wbm.config.service.impl;



import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.config.service.IndexConfigService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.stereotype.Service;

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
        return this.selectListByParams(queryParams);
    }

}
