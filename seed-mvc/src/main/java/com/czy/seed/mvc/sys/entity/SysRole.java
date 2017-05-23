package com.czy.seed.mvc.sys.entity;

import java.io.Serializable;

/**
 * Created by PLC on 2017/5/23.
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 4659702439449421452L;

    private Long id;
    private String code;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
