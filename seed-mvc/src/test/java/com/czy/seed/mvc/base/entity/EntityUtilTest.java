package com.czy.seed.mvc.base.entity;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Id;

/**
 * Created by 003914[panlc] on 2017-08-16.
 */
public class EntityUtilTest {

    class TestEntity{
        @Id
        private Long id;

        public Long getId() {
            return 11L;
        }

        public void setId(Long id) {
//            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }

    class TestEntityExtend extends TestEntity {
        private String add;

        public String getAdd() {
            return add;
        }

        public void setAdd(String add) {
            this.add = add;
        }
    }

    @Test
    public void getIdValule() throws Exception {
        TestEntity t = new TestEntity();
        t.setId(11L);
        t.setName("test");
        Long idValule = EntityUtil.getIdValule(t);
        Assert.assertTrue(11L == idValule);
    }

    @Test
    public void getIdValuleExtends() throws Exception {
        TestEntityExtend t = new TestEntityExtend();
        t.setId(11L);
        t.setName("test");
        Long idValule = EntityUtil.getIdValule(t);
        Long idValule2 = EntityUtil.getIdValule(t);
        Assert.assertTrue(11L == idValule);
    }

    @Test
    public void testSetPreparedValue() throws Exception {
        TestEntityExtend t = new TestEntityExtend();
        t.setId(11L);
        t.setName("test");
        EntityUtil.setPreparedValue(t, "name", "test2");
        Assert.assertTrue("test2".endsWith(t.getName()));
    }


}