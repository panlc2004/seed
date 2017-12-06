package com.czy.seed.mvc.sys.entity;

import com.czy.seed.mvc.base.entity.IPrepare;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "SEED_SYS_DICT_ITEM")
public class SysDictItem implements IPrepare {

    private static final long serialVersionUID = -6217382604447725238L;

    @Id
    private Long id;
    private Long parentId;
    private Long sysDictId;
    private String value;
    private String memo;
    private String name;
    private Long createBy;
    private Date createDt;
    private Date updateDt;
    private Long updateBy;
    private Integer orderNum;
    private int depth;


    @Transient   //标识数据库中没有的
    private int childNum;
    @Transient
    private List<SysDict> children;

    public Long getSysDictId() {
        return sysDictId;
    }

    public void setSysDictId(Long sysDictId) {
        this.sysDictId = sysDictId;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public List<SysDict> getChildren() {
        if (children == null) {
            children = new ArrayList<SysDict>(0);
        }
        return children;
    }

    public void setChildren(List<SysDict> children) {
        this.children = children;
    }
}
