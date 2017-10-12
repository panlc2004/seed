package com.czy.seed.mvc.sys.entity;

import com.czy.seed.mvc.base.entity.IPrepare;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "SEED_SYS_RESOURCE")
public class SysResource implements IPrepare{

    public SysResource() {
    }

    public SysResource(Long parentId, Long id) {
        this.id = id;
        this.parentId = parentId;
    }

    private static final long serialVersionUID = 8335380285204046588L;

    @Id
    private Long id;
    private Long parentId;   //父级目录
    private Integer types;   //资源类型
    private String code;     //资源编码
    private String name;     //资源名称
    private String url;      //资源链接
    private Integer orderBy;
    private String icon;     //资源图标
    private int depth;  //深度
    private Date createDt;
    private Long createBy;
    private Date updateDt;
    private Long updateBy;
    private Long sysOrgId;

    public Long getSysOrgId() {
        return sysOrgId;
    }

    public void setSysOrgId(Long sysOrgId) {
        this.sysOrgId = sysOrgId;
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

    @Transient
    private int childNum;
    @Transient
    private List<SysResource> children; //子资源

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

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
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

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysResource> getChildren() {
        if (children == null) {
            children = new ArrayList<SysResource>();
        }
        return children;
    }


    @Override
    public String toString() {
        return "SysResource{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", types=" + types +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", orderBy=" + orderBy +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }
}
