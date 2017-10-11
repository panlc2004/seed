package com.czy.seed.mvc.sys.model;

import java.util.List;

/**
 *
 */
public class Resources {
    /**
     * checkIds的父ID
     */
    private List<Long> parentIds;
    /**
     * 用户拥有的资源的ID
     */
    private List<Long> checkIds;
    /**
     * 所有的资源
     */
    private List<Resource> resourceData;

    public List<Long> getParentIds() {
        return parentIds;
    }

    public void setParentIds(List<Long> parentIds) {
        this.parentIds = parentIds;
    }

    public List<Long> getCheckIds() {
        return checkIds;
    }

    public void setCheckIds(List<Long> checkIds) {
        this.checkIds = checkIds;
    }

    public List<Resource> getResourceData() {
        return resourceData;
    }

    public void setResourceData(List<Resource> resourceData) {
        this.resourceData = resourceData;
    }
}
