package com.czy.seed.mvc.sys.entity;

import com.czy.seed.mvc.base.entity.IPrepare;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "SEED_SYS_DEPT")
public class SysDept implements IPrepare {

    private static final long serialVersionUID = 1172246239374961171L;

    @Id
    private Long id;
    private Date createDt;
    private Long createBy;
    private Date updateDt;
    private Long updateBy;
    private Long sysOrgId;
    private Long parentId;
    private int depth;
    private String code;
    private String name;
    private String memo;

    @Transient
    private int childNum;

    @Transient
    private List<SysDept> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
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

    public Long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(Long sysOrgId) {
        this.sysOrgId = sysOrgId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
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

    public List<SysDept> getChildren() {
        if (children == null) {
            children = new ArrayList<SysDept>(0);
        }
        return children;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }
}
