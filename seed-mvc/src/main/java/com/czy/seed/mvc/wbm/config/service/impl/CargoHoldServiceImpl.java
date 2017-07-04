package com.czy.seed.mvc.wbm.config.service.impl;

import com.czy.seed.mvc.base.service.impl.BaseServiceImpl;
import com.czy.seed.mvc.wbm.config.entity.type.AboutIndexConfigBean;
import com.czy.seed.mvc.wbm.config.entity.type.CargoHold;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.config.entity.type.PassengerCabin;
import com.czy.seed.mvc.wbm.config.mapper.IndexConfigMapper;
import com.czy.seed.mvc.wbm.config.service.CargoHoldService;
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
public class CargoHoldServiceImpl extends BaseServiceImpl<CargoHold> implements CargoHoldService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;


    @Override
    @Transactional("tm-default")
    public void saveAsList(List<AboutIndexConfigBean> indexConfigBeanList) {

        for (AboutIndexConfigBean indexConfigBean : indexConfigBeanList) {
            List<IndexConfig> indexConfigList = indexConfigBean.getIndexConfigList();
            CargoHold cargoHold = indexConfigBean.getCargoHold();
            //如果有ID 表明是修改动作
            if (cargoHold.getId() == null) {
                super.insert(cargoHold);
            } else {
                super.updateSelectiveByPrimaryKey(cargoHold);
            }
            QueryParams queryParams = new QueryParams(IndexConfig.class);
            QueryParams.Criteria criteria = queryParams.createCriteria();
            List<Long> list = new ArrayList<>();
            list.add(-1L);
            if (indexConfigList != null && !indexConfigList.isEmpty()) {
                for (IndexConfig indexConfig : indexConfigList) {
                    list.add(indexConfig.getId());
                    indexConfig.setAircraftCabinId(cargoHold.getId());
                    indexConfig.setTypes(2);
                }
            }
            criteria.andIn("id", list);
            indexConfigMapper.deleteByParams(queryParams);
            indexConfigMapper.insertList(indexConfigList);
        }


    }

    @Override
    public List<AboutIndexConfigBean> queryList(Long flightTypeConfigId) {
        QueryParams params = new QueryParams(CargoHold.class);
        QueryParams.Criteria criteria = params.createCriteria();
        criteria.andEqualTo("flightTypeConfigId", flightTypeConfigId);
        List<CargoHold> cargoHoldList = super.selectListByParams(params);
        if (cargoHoldList != null && !cargoHoldList.isEmpty()) {
            List<AboutIndexConfigBean> list = new ArrayList<>(cargoHoldList.size());
            for (CargoHold cargoHold : cargoHoldList) {
                QueryParams queryParams = new QueryParams(IndexConfig.class);
                QueryParams.Criteria queryParamsCriteria = queryParams.createCriteria();
                queryParamsCriteria.andEqualTo("flightTypeConfigId", flightTypeConfigId)
                        .andEqualTo("aircraftCabinId", cargoHold.getId())
                        .andEqualTo("types", 2);
                List<IndexConfig> indexConfigs = indexConfigMapper.selectListByParams(queryParams);
                AboutIndexConfigBean bean = new AboutIndexConfigBean();
                bean.setCargoHold(cargoHold);
                bean.setIndexConfigList(indexConfigs);
                list.add(bean);
            }
            return list;
        }

        return null;
    }
}
