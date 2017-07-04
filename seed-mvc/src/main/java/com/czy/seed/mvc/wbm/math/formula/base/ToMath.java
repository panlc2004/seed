package com.czy.seed.mvc.wbm.math.formula.base;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.FlightTypeConfig;
import com.czy.seed.mvc.wbm.config.entity.type.IndexConfig;
import com.czy.seed.mvc.wbm.math.formula.util.MathTool;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by panlc on 2017-04-24.
 * 起飞参数计算
 */
public class ToMath extends ZfMath {

    private BigDecimal tow;
    private BigDecimal toi;
    private BigDecimal toCg;
    private BigDecimal trimToCg;
    protected List<IndexConfig> fuelIndexConfigList;  //油量指数配置

    public ToMath(FlightInfo flightInfo) {
        super(flightInfo);
        //获取油量指数配置
        fuelIndexConfigList = indexConfigServiceImpl.findIndexConfigList(
                flightInfo.getFlightTypeConfig().getId(), 3, -1L);
        tow = buildTow();
        toi = buildFuelIndex(flightInfo.getFlightConfig().getTakeOffFuel());
        toCg = buildToCg();
        trimToCg = buildToTrimCg();
    }

    public BigDecimal getToCg() {
        return toCg;
    }

    public BigDecimal getTrimToCg() {
        return trimToCg;
    }

    public BigDecimal getTow() {
        return tow;
    }

    public BigDecimal getToi() {
        return toi;
    }

    /**
     * 计算实际起飞重量
     *
     * @return
     */
    private BigDecimal buildTow() {
        return super.getZfw()
                .add(super.getFlightInfo().getFlightConfig().getTakeOffFuel());
    }

    /**
     * 计算实际起飞指数
     *
     * @return
     */
    public BigDecimal buildFuelIndex(BigDecimal fuelWeight) {
        //计算起飞油量指数
        BigDecimal tofIndex = MathTool.linearInterpolation(fuelIndexConfigList, fuelWeight);
        return super.getZfi().add(tofIndex);
    }

    /**
     * 计算实际起飞重心
     *
     * @return
     */
    private BigDecimal buildToCg() {
        FlightTypeConfig flightTypeConfig = super.getFlightInfo().getFlightTypeConfig();
        return MathTool.buildCg(toi, tow, flightTypeConfig);
    }

    /**
     * 计算配平值
     *
     * @return
     */
    private BigDecimal buildToTrimCg() {
        if (toCg == null) {
            toCg = buildToCg();
        }
        return MathTool.trimBuildCg(toCg);
    }

}
