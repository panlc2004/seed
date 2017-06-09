package com.czy.seed.mvc.wbm.config.entity.type.types;

import java.math.BigDecimal;

/**
 * Created by panlc on 2017-05-02.
 */
public enum PassengerType {

    AUDLT {
        @Override
        public BigDecimal standardWeight(String flightType) {
            return null;
        }
    }, CHILD {
        @Override
        public BigDecimal standardWeight(String flightType) {
            return null;
        }
    }, INFANT {
        @Override
        public BigDecimal standardWeight(String flightType) {
            return null;
        }
    };

    /**
     * 每位乘客的标准重量
     */
    public abstract BigDecimal standardWeight(String flightType);



}
