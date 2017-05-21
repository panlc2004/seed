package com.czy.seed.mybatis.base.service;

import com.czy.seed.mybatis.entity.TestEntity;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PLC on 2017/4/30.
 */
public class BaseServiceImplTest {

    static ClassPathXmlApplicationContext ctx;
    static TestService service;

    @BeforeClass
    public static void beforeClass() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
        ctx.start();
        service = (TestService) ctx.getBean("testServiceImpl");
    }

    @Ignore
    @Test
    public void init() throws Exception {
        try {
            TestServiceImpl t = new TestServiceImpl();
            t.initMapper();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        service.insertList(testEntities);

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());
    }

    /********************
     * 性能测试：
     * ==>10000数据
     * ->每次新增加条数据，使用mapper的insert方法，方法使用transaction
     * SIMPLE:6035,4487,6236,6410,4784
     * BATCH:2516、2500、2531、2520
     * ==>100000数据
     * SIMPLE:30895,32141,30541
     * BATCH:10785,10698,14691
     *********************/


    @Test
    public void insertList2() {
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

        service.insertList(testEntities);

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());
    }
    /********************
     * 性能测试：
     * ==>10000数据
     * ->分次进行批次增加，使用mapper的insertList方法，方法使用transaction
     * SIMPLE:1742,2179,1644,1696,2237,2192,2145,2194,1692,1949,1652,2152,1509,1504,1566,
     * BATCH:1529,1509,1505,1536,1571,2051
     *
     * ==>100000数据
     * SIMPLE:3976,4076,5233,5201,5160,5097,3976,3967,3888
     * BATCH:3685,3632,3684,4199,3176,3603,3702,3670,3638
     * Ato_noxa/SIMPLE:4225,4142,4047,4129,4048,4142,4141,4326
     *********************/

    @Test
    public void insertList3() {
        int size = 10;
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

        service.insertList3(testEntities);

        Date end = new Date();
        System.out.println(end.getTime() - start.getTime());
    }
}