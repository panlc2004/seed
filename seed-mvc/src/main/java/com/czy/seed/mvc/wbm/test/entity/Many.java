package com.czy.seed.mvc.wbm.test.entity;

import javax.persistence.Id;

/**
 * Created by panlc on 2017-03-28.
 */
public class Many {

    @Id
    private Long id;
    private String name;
    private String memo;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
