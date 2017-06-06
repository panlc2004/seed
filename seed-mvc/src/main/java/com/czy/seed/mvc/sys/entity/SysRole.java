package com.czy.seed.mvc.sys.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by PLC on 2017/5/23.
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 4659702439449421452L;

    @Id
    private Long id;
    private String code;
    private String name;
    private String memo;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
