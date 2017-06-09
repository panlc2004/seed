package com.czy.seed.mvc.wbm.config.entity.type;

import com.czy.seed.mybatis.config.mybatis.annotations.ColumnType;
import com.czy.seed.mybatis.config.mybatis.annotations.One2Many;
import org.apache.ibatis.type.JdbcType;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 航班类型参数
 * Created by panlc on 2017-04-06.
 */
public class FlightTypeConfig  implements Serializable {

    @Id
    private Long id;
    private String flightType;                          //机型  UNIQUE KEY `flightType_uq` (`flightType`)
    private Integer seatNum;                            //最大座位数量
    @ColumnType(jdbcType = JdbcType.DECIMAL)
    private BigDecimal harm;                            //基准力臂
    @ColumnType(jdbcType = JdbcType.DECIMAL)
    private BigDecimal c;   //常量
    @ColumnType(jdbcType = JdbcType.DECIMAL)
    private BigDecimal k;   //常量
    @ColumnType(jdbcType = JdbcType.DECIMAL)
    private BigDecimal lemac;   //
    @ColumnType(jdbcType = JdbcType.DECIMAL)
    private BigDecimal datum;   //
    @ColumnType(jdbcType = JdbcType.DECIMAL)
    private BigDecimal mac;     //
    @One2Many(columns = "id=flight_Type_Config_Id")
    private List<CargoHold> cargoHoldList = new ArrayList<>(0);              //货物配置
    @One2Many(columns = "id=flight_Type_Config_Id")
    private List<GalleyGoods> galleyGoodsList;          //机供品信息
    @One2Many(columns = "id=flight_Type_Config_Id")
    private List<PassengerCabin> passengerCabinList = new ArrayList<>(0);    //客仓配置
    @One2Many(columns = "id=flight_Type_Config_Id")
    private List<Crew> crewList;                        //机组配置
    @One2Many(columns = "id=flight_Type_Config_Id")
    private List<Passenger> passengers;                 //乘客配置
    @One2Many(columns = "id=flight_Type_Config_Id")
    private List<Offset> offsetList;                    //偏差控制


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public List<CargoHold> getCargoHoldList() {
        return cargoHoldList;
    }

    public void setCargoHoldList(List<CargoHold> cargoHoldList) {
        this.cargoHoldList = cargoHoldList;
    }

    public List<GalleyGoods> getGalleyGoodsList() {
        return galleyGoodsList;
    }

    public void setGalleyGoodsList(List<GalleyGoods> galleyGoodsList) {
        this.galleyGoodsList = galleyGoodsList;
    }

    public List<PassengerCabin> getPassengerCabinList() {
        return passengerCabinList;
    }

    public void setPassengerCabinList(List<PassengerCabin> passengerCabinList) {
        this.passengerCabinList = passengerCabinList;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    public void setCrewList(List<Crew> crewList) {
        this.crewList = crewList;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Offset> getOffsetList() {
        return offsetList;
    }

    public void setOffsetList(List<Offset> offsetList) {
        this.offsetList = offsetList;
    }

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getK() {
        return k;
    }

    public void setK(BigDecimal k) {
        this.k = k;
    }

    public BigDecimal getHarm() {
        return harm;
    }

    public void setHarm(BigDecimal harm) {
        this.harm = harm;
    }

    public BigDecimal getLemac() {
        return lemac;
    }

    public void setLemac(BigDecimal lemac) {
        this.lemac = lemac;
    }

    public BigDecimal getDatum() {
        return datum;
    }

    public void setDatum(BigDecimal datum) {
        this.datum = datum;
    }

    public BigDecimal getMac() {
        return mac;
    }

    public void setMac(BigDecimal mac) {
        this.mac = mac;
    }



}
