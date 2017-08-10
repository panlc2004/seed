package com.czy.seed.mvc.base.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
