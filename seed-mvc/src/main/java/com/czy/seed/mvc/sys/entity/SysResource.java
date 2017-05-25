package com.czy.seed.mvc.sys.entity;

import java.io.Serializable;

/**
 * 系统资源
 * Created by PLC on 2017/5/23.
 */
public class SysResource implements Serializable{
    private static final long serialVersionUID = 8335380285204046588L;
    private Long id;
    private Long parentId;  //父级目录
    private Integer type;   //资源类型
    private String code;    //资源编码
    private String name;    //名称
    private String url;     //资源链接

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
