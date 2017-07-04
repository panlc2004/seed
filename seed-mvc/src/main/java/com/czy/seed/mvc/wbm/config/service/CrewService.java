package com.czy.seed.mvc.wbm.config.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.wbm.config.entity.type.AboutIndexConfigBean;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;

import java.util.List;

/**
 * Created by panlc on 2017-05-04.
 */
public interface CrewService extends BaseService<Crew> {
    /**
     * 保存和修改乘务信息,在列表集合中修改操作先直接删除列表数据,再新增列表数据
     *
     * @param crewList 乘务信息
     * @return
     */
    void saveAsList(List<Crew> crewList);
}
