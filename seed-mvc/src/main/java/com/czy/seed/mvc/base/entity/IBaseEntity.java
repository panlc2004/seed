package com.czy.seed.mvc.base.entity;

import java.util.Date;

public interface IBaseEntity{
    Date getCreateDt() ;

    void setCreateDt(Date createDt);

    Long getCreateBy() ;

    void setCreateBy(Long createBy) ;

    Date getUpdateDt();

    void setUpdateDt(Date updateDt) ;

    Long getUpdateBy();

    void setUpdateBy(Long updateBy) ;

    Integer getVersion();

    void setVersion(Integer version) ;

    Integer getLogicDel();

    void setLogicDel(Integer logicDel);

}
