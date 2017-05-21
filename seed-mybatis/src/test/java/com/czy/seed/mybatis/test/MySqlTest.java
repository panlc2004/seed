package com.czy.seed.mybatis.test;

import com.czy.seed.mybatis.base.QueryParams;
import com.czy.seed.mybatis.entity.TestEntity;
import com.czy.seed.mybatis.mapper.MySqlMapper;
import com.czy.seed.mybatis.service.MysqlService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by panlc on 2017-03-24.
 */
public class MySqlTest {

    static ClassPathXmlApplicationContext ctx;
    static MySqlMapper mySqlMapper;

    @BeforeClass
    public static void beforeClass() {
        ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
        ctx.start();
        mySqlMapper = ctx.getBean(MySqlMapper.class);
    }

    @Test
    public void testInsertAndDeleteByPrimaryKeyAndUpdate() {
        TestEntity testEntity = new TestEntity();
//        testEntity.setName("tsetest");
//        long insert = mySqlMapper.insert(testEntity);
        TestEntity testEntity2 = new TestEntity();
        testEntity2.setId(1L);
        long insert2 = mySqlMapper.insert(testEntity2);
        Assert.assertTrue(testEntity.getId() != null);
        testEntity.setName("q23");
        int i = mySqlMapper.updateByPrimaryKey(testEntity);
        int delete = mySqlMapper.deleteByPrimaryKey(testEntity.getId());
    }

    @Test
    public void testInsertList() {
        List<TestEntity> tList = new ArrayList<TestEntity>(2);

        TestEntity testEntity = new TestEntity();
        testEntity.setId(11L);
        TestEntity testEntity2 = new TestEntity();
//        testEntity2.setId(12L);
        TestEntity testEntity3 = new TestEntity();
//        testEntity3.setId(13L);


        tList.add(testEntity);
        tList.add(testEntity2);
        tList.add(testEntity3);

        int i = mySqlMapper.insertList(tList);
        System.out.println(2);
    }

    @Test
    public void testSelectByPrimaryKey() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("tsetest");
        long insert = mySqlMapper.insert(testEntity);
        Long id = testEntity.getId();
        TestEntity testEntity1 = mySqlMapper.selectByPrimaryKey(id);
        Assert.assertTrue(id == testEntity1.getId());
    }

    @Test
    public void testSelectListByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        queryParams.orderBy("id").asc().orderBy("name").desc();
        queryParams.selectProperties("name");
        QueryParams.Criteria criteria = queryParams.createCriteria();
        QueryParams.Criteria or = queryParams.or();
        criteria.andEqualTo("name", "123");
        criteria.andLike("name", "%et%");
        criteria.andBetween("id", 10, 13);
        criteria.andCondition("1=-1");
        List<TestEntity> testEntities = mySqlMapper.selectListByParams(queryParams);
        System.out.println(testEntities);
    }

    @Test
    public void testSelectOneByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
//        queryParams.selectProperties("name");
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("id", 27);
        TestEntity testEntity = mySqlMapper.selectOneByParams(queryParams);
        System.out.println(testEntity.getId());
    }

    @Test
    public void testDeleteByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
//        criteria.andEqualTo("name", "et");
        int i = mySqlMapper.deleteByParams(queryParams);
        Assert.assertTrue(i == 0);
    }

    @Test
    public void testUpdateSelectiveByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("name", "et");
        TestEntity testEntity = new TestEntity();
        testEntity.setName("123");
        int i = mySqlMapper.updateSelectiveByParams(testEntity, null);
    }

    @Test
    public void testUpdateByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("name", "et");
        TestEntity testEntity = new TestEntity();
        int i = mySqlMapper.updateByParams(testEntity, queryParams);
    }

    @Test
    public void testSelectCountByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("name", "tsetest");
        int i = mySqlMapper.selectCountByParams(queryParams);
        System.out.println(i);
    }

    @Test
    public void testSelect() {
        TestEntity testEntity = mySqlMapper.selectByPrimaryKey1(1);
        System.out.println(testEntity);
    }

    @Test
    public void testSelectRelativeByPrimaryKey() {
        TestEntity testEntity = mySqlMapper.selectRelativeByPrimaryKey(2);
        System.out.println(testEntity);
        TestEntity testEntity2 = mySqlMapper.selectRelativeByPrimaryKey(2);
        System.out.println(testEntity2);

    }

    @Test
    @Transactional()
    public void testSelectListRelativeByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        queryParams.orderBy("id").asc().orderBy("name").desc();
//        QueryParams.Criteria or = queryParams.or();
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andLike("name", "%et%").andCondition("ONE_one.id = 1");
//        or.andEqualTo("id", 12);
        List<TestEntity> testEntities = mySqlMapper.selectListRelativeByParams(queryParams);
        System.out.println(testEntities);
    }

    @Test
    public void testSelectOneRelativeByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andLike("name", "%et%").andCondition("ONE_oneList.id = 3");
        criteria.andEqualTo("id", 20);
        TestEntity testEntity = mySqlMapper.selectOneRelativeByParams(queryParams);
        System.out.println(testEntity);
    }

    @Test
    public void testSelectRelativeByPrimaryKey2() {
        TestEntity testEntity = mySqlMapper.selectRelativeByPrimaryKey2(2);
        System.out.println(testEntity);
    }

    @Test
    public void testCallPro() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        Object i = mySqlMapper.callPro(map);
        System.out.println(i);
    }

    @Test
    public void testCallPro3() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        Object i = mySqlMapper.callPro3(map);
        System.out.println(i);
    }

    @Test
    public void testRollback() throws Exception {
        MysqlService mysqlService = (MysqlService) ctx.getBean("mysqlService");
        mysqlService.testRollback();

    }

    @Test
    public void tset4() throws Exception {
        List<TestEntity> testEntities = mySqlMapper.selectListByParams(null);
        System.out.println(testEntities);
    }

    @Test
    public void insertList() throws Exception {
        Date start = new Date();
        System.out.println(start);
        List<TestEntity> testEntities = new ArrayList<TestEntity>(3);
        for (int i = 0; i < 3; i++) {
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

        mySqlMapper.insertList(testEntities);
        Date end = new Date();
        System.out.println(end);
        System.out.println(testEntities);
        System.out.println("user:" + (start.getTime() - end.getTime()));    //-568
    }

    @Test
    public void insertList2() throws Exception {
        Date start = new Date();
        System.out.println(start);
        int size = 10000;
        List<TestEntity> testEntities = new ArrayList<TestEntity>(size);
        for (int i = 0; i < size; i++) {
            TestEntity t1 = new TestEntity();
            t1.setName("t1");
            mySqlMapper.insert(t1);
        }
        Date end = new Date();
        System.out.println(end);
        System.out.println(testEntities);
        System.out.println("user:" + (start.getTime() - end.getTime()));
        //list.size = 1000 ->
        // [simple:-7849 ; batch:-7525]
        // [simple:-7770;batch:-7676]
        // [simple:-7940;batch:-9276]
        // [simple:-7728;batch:-8458]
        // [simple:-10152;batch:-7493]
        // [simple:-9477;batch:-7722]

        //list.size = 10000 ->
        // [simple:-68879;batch:-7722]
        // [simple:-9477;batch:-7722]
        // [simple:-9477;batch:-7722]
        // [simple:-9477;batch:-7722]
        // [simple:-9477;batch:-7722]
        // [simple:-9477;batch:-7722]
        // [simple:-9477;batch:-7722]
        // [simple:-9477;batch:-7722]
        // [simple:-9477;batch:-7722]


        //list.size = 10000 -> simple:-7849 ; batch:-7525
    }

    @Test
    public void test3() {
        MysqlService mysqlService = (MysqlService) ctx.getBean("mysqlService");
        int size = 1000000;
        List<TestEntity> testEntities = new ArrayList<TestEntity>(size);
        Date start = new Date();
        System.out.println(start);
        for (int i = 0; i < size; i++) {
            TestEntity t1 = new TestEntity();
            t1.setName("t1");
            if (i == 1) {
                t1.setName("name");
            }
            if (i == 2) {
//                t1.setId(1L);
            }
            testEntities.add(t1);
        }
        mysqlService.insertList2(testEntities);
        Date end = new Date();
        System.out.println(end);
        System.out.println(testEntities);
        System.out.println("user:" + (start.getTime() - end.getTime()));
    }

    @Test
    public void test4() {
        int size = 3;
        List<TestEntity> testEntities = new ArrayList<TestEntity>(size);
        Date start = new Date();
        System.out.println(start);
        for (int i = 0; i < size; i++) {
            TestEntity t1 = new TestEntity();
            t1.setName("t1");
            if (i == 1) {
                t1.setName("name");
            }
            if (i == 2) {
//                t1.setId(1L);
            }
            testEntities.add(t1);
        }
        mySqlMapper.insertList1(testEntities);
        System.out.println(testEntities);
    }

}
