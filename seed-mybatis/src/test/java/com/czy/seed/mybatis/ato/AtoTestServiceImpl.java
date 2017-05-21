package com.czy.seed.mybatis.ato;

import com.czy.seed.mybatis.base.service.BaseServiceImpl;
import com.czy.seed.mybatis.base.service.TestService;
import com.czy.seed.mybatis.entity.TestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by PLC on 2017/5/1.
 */
@Service()
public class AtoTestServiceImpl extends BaseServiceImpl<TestEntity> implements AtoTestService {

    @Resource
    private TestService testService;

    @Transactional
    public int insertBetweenDiffDb(List<TestEntity> recordList) {
        int i = recordList.size() / 2;
        super.insertList(recordList.subList(0, i));
        List<TestEntity> recordList1 = recordList.subList(i, recordList.size());
        recordList.get(50).setId(1L);
        testService.insertList(recordList1);
        return 12;
    }

    @Override
    public String getMapperName() {
        return "AtoTestMapper";
    }
}
