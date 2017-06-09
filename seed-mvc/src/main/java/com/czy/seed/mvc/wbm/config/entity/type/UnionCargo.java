package com.czy.seed.mvc.wbm.config.entity.type;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 联合仓位配置
 * Created by panlc on 2017-04-28.
 */
public class UnionCargo implements Serializable {

    private static final long serialVersionUID = -3046623180941260638L;

    @Id
    private Long id;
    private String unionCargoCode;          //联合仓位代码
    private BigDecimal unionCargoWeight;    //联合重量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnionCargoCode() {
        return unionCargoCode;
    }

    public void setUnionCargoCode(String unionCargoCode) {
        this.unionCargoCode = unionCargoCode;
    }

    public BigDecimal getUnionCargoWeight() {
        return unionCargoWeight;
    }

    public void setUnionCargoWeight(BigDecimal unionCargoWeight) {
        this.unionCargoWeight = unionCargoWeight;
    }
}
