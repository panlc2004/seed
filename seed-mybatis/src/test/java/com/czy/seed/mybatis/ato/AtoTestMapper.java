package com.czy.seed.mybatis.ato;

import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;
import com.czy.seed.mybatis.entity.TestEntity;

/**
 * Created by PLC on 2017/5/1.
 */
@AutoMapper("ds1")
public interface AtoTestMapper extends BaseMapper<TestEntity> {

}
