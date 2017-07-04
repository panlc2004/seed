package com.czy.seed.mvc.wbm.test.test;

import com.czy.seed.mvc.wbm.test.entity.TestEntity;
import com.czy.seed.mvc.wbm.test.mapper.MySqlMapper;
import com.czy.seed.mybatis.base.QueryParams;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by panlc on 2017-03-24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MySqlTest {

    static ClassPathXmlApplicationContext ctx;

    @Autowired
    static MySqlMapper mySqlMapper;

    @BeforeClass
    public static void beforeClass() {
//        ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
//        ctx.start();
//        mySqlMapper = ctx.getBean(MySqlMapper.class);
    }

    @Test
    public void testInsertAndDeleteByPrimaryKeyAndUpdate() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("tsetest");
        long insert = mySqlMapper.insert(testEntity);
        Assert.assertTrue(testEntity.getId() != null);
        int i = mySqlMapper.updateByPrimaryKey(testEntity);
        Assert.assertEquals(1, i);
        int delete = mySqlMapper.deleteByPrimaryKey(testEntity.getId());
        Assert.assertEquals(1, delete);
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
        criteria.andEqualTo("name", "123");
        criteria.andLike("name", "%et%");
        criteria.andBetween("id", 10, 13);
//        criteria.andCondition("1=-1");
        List<TestEntity> testEntities = mySqlMapper.selectListByParams(queryParams);
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
        criteria.andEqualTo("name", "et");
        int i = mySqlMapper.deleteByParams(queryParams);
        Assert.assertTrue(i == 0);
    }

    @Test
    public void testUpdateSelectiveByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andEqualTo("name", "et");
        TestEntity testEntity = new TestEntity();
        testEntity.setName("tsetest");
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
    public void testInsert2() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("tsetest");
        mySqlMapper.insert2(testEntity);
        Assert.assertTrue(testEntity.getId() != null);
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
    }

    @Test
    public void testSelectListRelativeByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        queryParams.orderBy("id").asc().orderBy("name").desc();
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andLike("name", "%et%").andCondition("ONE_one.id = 1");
        List<TestEntity> testEntities = mySqlMapper.selectListRelativeByParams(queryParams);
        System.out.println(testEntities);
    }

    @Test
    public void testSelectOneRelativeByParams() {
        QueryParams queryParams = new QueryParams(TestEntity.class);
        QueryParams.Criteria criteria = queryParams.createCriteria();
        criteria.andLike("name", "%et%").andCondition("ONE_oneList.id = 3");
        TestEntity testEntity = mySqlMapper.selectOneRelativeByParams(queryParams);
        System.out.println(testEntity);
    }

    @Test
    public void testSelectRelativeByPrimaryKey2() {
        TestEntity testEntity = mySqlMapper.selectRelativeByPrimaryKey2(2);
        System.out.println(testEntity);
    }

}
