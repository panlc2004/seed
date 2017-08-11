package com.czy.seed.mvc.sys.entity;

import com.czy.seed.mvc.base.entity.PrepareEntity;

import javax.persistence.Table;

@Table(name = "SEED_SYS_DICT_ITEM")
public class SysDictItem extends PrepareEntity {
    private Long parentId;
    private String sysDictCode;
    private String itemCode;
    private String value;
    private String memo;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSysDictCode() {
        return sysDictCode;
    }

    public void setSysDictCode(String sysDictCode) {
        this.sysDictCode = sysDictCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
