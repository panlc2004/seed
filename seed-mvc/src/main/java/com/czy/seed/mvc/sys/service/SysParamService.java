package com.czy.seed.mvc.sys.service;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysParam;

public interface SysParamService extends BaseService<SysParam> {

    /**
     * 该功能用于激活用户
     * @param id 主键
     * @param active 是否激活
     * @return  无返回值
     */
    void updateActiveByPrimaryKey(long id, long active);


}
