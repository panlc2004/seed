package com.czy.seed.mvc.wbm.config.service.impl;


import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.wbm.config.entity.type.AboutIndexConfigBean;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.config.entity.type.PassengerCabin;
import com.czy.seed.mvc.wbm.config.mapper.IndexConfigMapper;
import com.czy.seed.mvc.wbm.config.service.PassengerCabinService;
import com.czy.seed.mybatis.base.QueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@Service
public class PassengerCabinServiceImpl extends BaseServiceImpl<PassengerCabin> implements PassengerCabinService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Override
    @Transactional("tm-default")
    public void saveAsList(List<AboutIndexConfigBean> indexConfigBeanList) {
        for (AboutIndexConfigBean aboutIndexConfigBean : indexConfigBeanList) {
            PassengerCabin cabin = aboutIndexConfigBean.getCabin();
            if (cabin.getId() == null) {
                super.insert(cabin);
            } else {
                super.updateByPrimaryKey(cabin);
            }

            List<IndexConfig> indexConfigList = aboutIndexConfigBean.getIndexConfigList();

            QueryParams queryParams = new QueryParams(IndexConfig.class);
            QueryParams.Criteria criteria = queryParams.createCriteria();
            List<Long> list = new ArrayList<>();
            list.add(-1L);
            if (indexConfigList != null && !indexConfigList.isEmpty()) {
                for (IndexConfig indexConfig : indexConfigList) {
                    list.add(indexConfig.getId());
                    indexConfig.setAircraftCabinId(cabin.getId());
                    indexConfig.setTypes(1);
                }
            }
            criteria.andIn("id", list);
            indexConfigMapper.deleteByParams(queryParams);
            indexConfigMapper.insertList(indexConfigList);
        }
    }

    @Override
    public List<AboutIndexConfigBean> queryList(Long flightTypeConfigId) {
        QueryParams params = new QueryParams(PassengerCabin.class);
        QueryParams.Criteria criteria = params.createCriteria();
        criteria.andEqualTo("flightTypeConfigId", flightTypeConfigId);
        List<PassengerCabin> cabinList = super.selectListByParams(params);
        if (cabinList != null && !cabinList.isEmpty()) {
            List<AboutIndexConfigBean> list = new ArrayList<>(cabinList.size());
            for (PassengerCabin cabin : cabinList) {
                QueryParams queryParams = new QueryParams(IndexConfig.class);
                QueryParams.Criteria queryParamsCriteria = queryParams.createCriteria();
                queryParamsCriteria.andEqualTo("flightTypeConfigId", flightTypeConfigId)
                        .andEqualTo("aircraftCabinId", cabin.getId())
                        .andEqualTo("types", 1);
                List<IndexConfig> indexConfigs = indexConfigMapper.selectListByParams(queryParams);
                AboutIndexConfigBean bean = new AboutIndexConfigBean();
                bean.setCabin(cabin);
                bean.setIndexConfigList(indexConfigs);
                list.add(bean);
            }
            return list;
        }

        return null;
    }
}
