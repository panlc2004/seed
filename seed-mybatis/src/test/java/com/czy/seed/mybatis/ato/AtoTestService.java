package com.czy.seed.mybatis.ato;

import com.czy.seed.mybatis.base.service.BaseService;
import com.czy.seed.mybatis.entity.TestEntity;

import java.util.List;

/**
 * Created by PLC on 2017/5/1.
 */
public interface AtoTestService extends BaseService<TestEntity> {
    int insertBetweenDiffDb(List<TestEntity> recordList);
}
