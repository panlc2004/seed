package com.czy.seed.mvc.sys.model;

import java.util.List;

public class Resource{
    /**
     * id
     */
    private Long id;

    /**
     * 资源的名字
     */
    private String label;

    /**
     * 子元素
     */
    private List<Resource> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }
}
