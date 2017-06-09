package com.czy.seed.mvc.wbm.test.entity;

import com.czy.seed.mybatis.config.mybatis.annotations.One2Many;
import com.czy.seed.mybatis.config.mybatis.annotations.One2One;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by panlc on 2017-03-28.
 */
@Table(name = "test")
public class TestEntity {
    @Id
    private Long id;
    private String name;

    @One2One(columns = "id=id")
    private One one;

    @One2One(columns = "id=test_id")
    private One one1;

    @One2Many(columns = "name=name")
    private List<One> oneList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
