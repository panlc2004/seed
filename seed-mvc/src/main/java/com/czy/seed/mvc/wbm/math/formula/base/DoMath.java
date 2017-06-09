package com.czy.seed.mvc.wbm.math.formula.base;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.Crew;
import com.czy.seed.mvc.wbm.config.entity.type.GalleyGoods;
import com.czy.seed.mvc.wbm.config.entity.type.types.Position;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by panlc on 2017-04-24.
 * 干操作参数公式计算
 */
public class DoMath extends Math {

    private BigDecimal dow;
    private BigDecimal doi;

    public DoMath(FlightInfo flightInfo) {
        super(flightInfo);
        dow = buildDow();
        doi = buildDoi();
    }

    /**
     * 获取干操作重量
     *
     * @return 干操作重量
     */
    public BigDecimal getDow() {
        return dow;
    }

    /**
     * 获取干操作指数
     *
     * @return 干操作指数
     */
    public BigDecimal getDoi() {
        return doi;
    }

    /**
     * 计算干操作重量
     *
     * @return
     */
    private BigDecimal buildDow() {
        FlightInfo flightInfo = super.getFlightInfo();

        //基本重量
        BigDecimal baseWeight = flightInfo.getFlightConfig().getBaseWeight();

        //飞行员重量
        Map<Position, List<Crew>> crewGroup = getCrewGroup();
        BigDecimal fwdCrewWeight = getCrewWeight(crewGroup, Position.COCKPIT);      //飞机员
        BigDecimal midCrewWeight = getCrewWeight(crewGroup, Position.FWD);          //前舱空乘
        BigDecimal aftCrewWeight = getCrewWeight(crewGroup, Position.AFT);          //后舱空乘

        //机供品重量
        Map<Position, List<GalleyGoods>> galleyGroup = getGalleyGroup();
        BigDecimal fwdGalleyWeight = getGalleyWeight(galleyGroup, Position.FWD);    //前舱
        BigDecimal aftGalleyWeight = getGalleyWeight(galleyGroup, Position.AFT);    //后舱

        //干操作重量
        return baseWeight.add(fwdCrewWeight)
                .add(midCrewWeight)
                .add(aftCrewWeight)
                .add(fwdGalleyWeight)
                .add(aftGalleyWeight);
    }

    /**
     * 计算干操作指数
     *
     * @return
     */
    private BigDecimal buildDoi() {
        FlightInfo flightInfo = super.getFlightInfo();

        //基本指数
        BigDecimal baseIndex = flightInfo.getFlightConfig().getBaseIndex();
        Map<Position, List<Crew>> crewGroup = getCrewGroup();
        BigDecimal cockpitIndex = getCrewIndex(crewGroup, Position.COCKPIT);    //机组指数
        BigDecimal fwdIndex = getCrewIndex(crewGroup, Position.FWD);            //前舱空乘指数
        BigDecimal aftIndex = getCrewIndex(crewGroup, Position.AFT);            //后舱空乘指数

        //前仓机供品指数
        Map<Position, List<GalleyGoods>> galleyGroup = getGalleyGroup();
        BigDecimal fwdGalleyIndex = getGalleyIndex(galleyGroup, Position.FWD);
        BigDecimal aftGalleyIndex = getGalleyIndex(galleyGroup, Position.AFT);

        //干操作指数
        return baseIndex
                .add(cockpitIndex)
                .add(fwdIndex)
                .add(aftIndex)
                .add(fwdGalleyIndex)
                .add(aftGalleyIndex)
                .divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 机组人员分组
     *
     * @return
     */
    public Map<Position, List<Crew>> getCrewGroup() {
        FlightInfo flightInfo = super.getFlightInfo();
        return flightInfo.getFlightTypeConfig().getCrewList().stream()
                .collect(Collectors.groupingBy(crew -> crew.getPosition()));
    }

    /**
     * 计算机组人员重量
     *
     * @param crewGroup
     * @param position
     * @return
     */
    private BigDecimal getCrewWeight(Map<Position, List<Crew>> crewGroup, Position position) {
        if (crewGroup.containsKey(position)) {
            Crew fwdCrew = crewGroup.get(position).get(0);
            return getCrewDiffNum(crewGroup, position)
                    .multiply(fwdCrew.getWeight());
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getCrewDiffNum(Map<Position, List<Crew>> crewGroup, Position position) {
        if (!crewGroup.containsKey(position)) {
            return BigDecimal.ZERO;
        }
        Crew fwdCrew = crewGroup.get(position).get(0);
        return fwdCrew.getActuralNum()
                .subtract(fwdCrew.getStandardNum());
    }

    /**
     * 机供品分组
     *
     * @return
     */
    private Map<Position, List<GalleyGoods>> getGalleyGroup() {
        List<GalleyGoods> galleyGoodsList = super.getFlightInfo().getFlightTypeConfig().getGalleyGoodsList();
        if (galleyGoodsList == null) {
            return new HashMap<>();
        }
        return galleyGoodsList.stream()
                .collect(Collectors.groupingBy(galley -> galley.getPosition()));
    }

    /**
     * 计算机供品重量
     *
     * @param galleyGroup
     * @param position
     * @return
     */
    private BigDecimal getGalleyWeight(Map<Position, List<GalleyGoods>> galleyGroup, Position position) {
        if (!galleyGroup.containsKey(position)) {
            return BigDecimal.ZERO;
        } else {
            GalleyGoods galleyGoods = galleyGroup.get(position).get(0);
            if (galleyGoods.getActualWeight().compareTo(galleyGoods.getAlarmWeight()) >= 0) {
                throw new IllegalArgumentException(position.getName()
                        + "机供品实际配置重量大于或等于报警值[" + galleyGoods.getAlarmWeight() + "]");
            }
            return galleyGoods.getActualWeight().subtract(galleyGoods.getStandardWeight());
        }
    }


    private BigDecimal getCrewIndex(Map<Position, List<Crew>> crewGroup, Position position) {
        if (!crewGroup.containsKey(position)) {
            throw new IllegalArgumentException(position.getName() + "机组参数未初始化");
        }
        Crew crew = crewGroup.get(position).get(0);
        return crew.getActuralNum()
                .subtract(crew.getStandardNum())
                .multiply(crew.getStandardIndex());
    }

    private BigDecimal getGalleyIndex(Map<Position, List<GalleyGoods>> galleyGroup, Position position) {
        if (!galleyGroup.containsKey(position)) {
            throw new IllegalArgumentException(position.getName() + "机供品参数未初始化");
        }
        GalleyGoods galleyGoods = galleyGroup.get(position).get(0);
        return galleyGoods.getActualWeight()
                .subtract(galleyGoods.getStandardWeight())
                .multiply(galleyGoods.getStandardIndex())
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    }

}
