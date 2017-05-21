package com.czy.seed.mybatis.ato;

import com.czy.seed.mybatis.entity.TestEntity;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PLC on 2017/4/30.
 */
public class TestServiceImplTest {

    static ClassPathXmlApplicationContext ctx;
    static AtoTestService service;

    @BeforeClass
    public static void beforeClass() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
        ctx.start();
        service = (AtoTestService) ctx.getBean("atoTestServiceImpl");
    }



    @Test
    public void insertList() {
        int size = 100000;
        List<TestEntity> testEntities = new ArrayList<TestEntity>(size);
        for (int i = 0; i < size; i++) {
            TestEntity t1 = new TestEntity();
            t1.setName("t1");
            if (i == 1) {
                t1.setName("name");
            }
            if (i == 2) {
                t1.setId(1L);
            }
            testEntities.add(t1);
        }

        Date start = new Date();

        service.insertBetweenDiffDb(testEntities);

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());
    }

}