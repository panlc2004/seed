package com.czy.seed.mvc.wbm.config.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.entity.type.GalleyGoods;

import java.util.List;

/**
 * Created by panlc on 2017-05-04.
 */
public interface GalleyGoodsService extends BaseService<GalleyGoods> {
    /**
     * 保存和修改机供品信息,在列表集合中修改操作先直接删除列表数据,再新增列表数据
     *
     * @param galleyGoodsList 机供品列表
     * @return
     */
    void saveAsList(List<GalleyGoods> galleyGoodsList);
}
