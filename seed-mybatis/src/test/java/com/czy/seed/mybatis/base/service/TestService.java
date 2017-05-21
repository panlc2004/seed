package com.czy.seed.mybatis.base.service;

import com.czy.seed.mybatis.entity.TestEntity;

import java.util.List;

/**
 * Created by PLC on 2017/4/30.
 */
public interface TestService extends BaseService<TestEntity> {
    void insertList2(List<TestEntity> recordList);
    void insertList3(List<TestEntity> recordList);
}
