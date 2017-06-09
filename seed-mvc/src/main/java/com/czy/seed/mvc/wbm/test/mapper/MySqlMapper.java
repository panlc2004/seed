package com.czy.seed.mvc.wbm.test.mapper;

import com.czy.seed.mvc.wbm.test.entity.TestEntity;
import com.czy.seed.mybatis.base.mapper.BaseMapper;
import com.czy.seed.mybatis.config.mybatis.annotations.AutoMapper;

/**
 * Created by panlc on 2017-03-17.
 */
@AutoMapper()
public interface MySqlMapper extends BaseMapper<TestEntity> {

    int insert2(TestEntity recore);

    TestEntity selectByPrimaryKey1(long key);

    TestEntity selectRelativeByPrimaryKey2(long key);

}
