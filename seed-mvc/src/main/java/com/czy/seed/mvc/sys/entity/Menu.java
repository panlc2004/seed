package com.czy.seed.mvc.sys.entity;

import java.util.List;

public class Menu {

    private List<Menu> children;

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
