package com.czy.seed.mvc.wbm.math.formula.base;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.FlightTypeConfig;
import com.czy.seed.mvc.wbm.math.formula.util.MathTool;

import java.math.BigDecimal;

/**
 * Created by panlc on 2017-04-24.
 * 落地参数计算
 */
public class LdMath extends ToMath {

    private BigDecimal ldw;
    private BigDecimal ldi;
    private BigDecimal ldCg;


    public LdMath(FlightInfo flightInfo) {
        super(flightInfo);
        this.ldw = buildLdw();
        this.ldi = buildLdi();
        this.ldCg = buildLdCg();
    }

    public BigDecimal getLdw() {
        return ldw;
    }

    public BigDecimal getLdi() {
        return ldi;
    }

    public BigDecimal getLdCg() {
        return ldCg;
    }

    /**
     * 计算落地重量
     * @return
     */
    private BigDecimal buildLdw() {
        return super.getTow()
                .subtract(super.getFlightInfo().getFlightConfig().getTripFuel());
    }

    /**
     * 计算落地指数
     * @return
     */
    private BigDecimal buildLdi() {
        return super.getToi().subtract(super.buildFuelIndex(super.getFlightInfo().getFlightConfig().getTripFuel()));
    }

    /**
     * 计算落地重心
     * @return
     */
    private BigDecimal buildLdCg() {
        FlightTypeConfig flightTypeConfig = super.getFlightInfo().getFlightTypeConfig();
        return MathTool.buildCg(ldi, ldw, flightTypeConfig);
    }

    /**
     * 计算配平值
     * @return
     */
    private BigDecimal buildLdTrimCg() {
        if (ldCg == null) {
            ldCg = buildLdCg();
        }
        return MathTool.trimBuildCg(ldCg);
    }

}
