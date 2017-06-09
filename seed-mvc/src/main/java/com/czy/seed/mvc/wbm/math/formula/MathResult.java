package com.czy.seed.mvc.wbm.math.formula;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.FlightTypeConfig;

import java.math.BigDecimal;

/**
 * Created by panlc on 2017-04-24.
 */
public class MathResult {

    private FlightInfo flightInfo;

    private FlightTypeConfig flightTypeConfig;


    public MathResult(FlightInfo flightInfo, FlightTypeConfig flightTypeConfig) {
        this.flightInfo = flightInfo;
        this.flightTypeConfig = flightTypeConfig;
    }


    /*=====================================================
     *                  干操作参数计算
     =====================================================*/
    /**
     * 获取干操作指数
     *
     * @return
     */
    public BigDecimal getDoi() {
        return null;
    }

    /*=====================================================
    *                  无油参数计算
    =====================================================*/
   /**
    * 获取实际无油重量
    *
    * @return
    */
    public BigDecimal getZfw() {
        return null;
    }

    /**
     * 获取无油操作指数
     *
     * @return
     */
    public BigDecimal getZfi() {
        return null;
    }

    /**
     * 获取无油重心
     *
     * @return
     */
    public BigDecimal getZfCg() {
        return null;
    }

    /*=====================================================
    *                  起飞参数计算
    =====================================================*/
   /**
    * 获取实际起飞重量
    *
    * @return
    */
    public BigDecimal getTow() {
        return null;
    }

    /**
     * 获取实际起飞指数
     *
     * @return
     */
    public BigDecimal getToi() {
        return null;
    }

    /**
     * 获取实际起飞重心
     *
     * @return
     */
    public BigDecimal towCg() {
        return null;
    }

    /**
     * //TODO
     *
     * @return
     */
    public BigDecimal trimTow() {
        return null;
    }

    /*=====================================================
    *                  落地参数计算
    =====================================================*/
    /**
     * 获取实际落地重量
     *
     * @return
     */
    public BigDecimal getLdw() {
        return null;
    }

    /**
     * 获取实际落地指数
     *
     * @return
     */
    public BigDecimal getLdi() {
        return null;
    }

    /**
     * 获取实际落地重心
     *
     * @return
     */
    public BigDecimal getLdwCg() {
        return null;
    }

    /**
     * //TODO
     *
     * @return
     */
    public BigDecimal getTrimLdw() {
        return null;
    }

}
