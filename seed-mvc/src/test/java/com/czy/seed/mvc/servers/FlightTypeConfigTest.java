/*
 * 文 件 名 : FlightTypeConfigTest.java
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <用户实体>
 * 修 改 人 : <003912>wuchaoliang@inner.czy.com
 * 修改时间 : 2017年6月9日 上午10:53:25
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.servers;

import com.czy.seed.mvc.wbm.config.controller.FlightTypeConfigController;
import com.czy.seed.mvc.wbm.config.entity.EchoConfBean;
import com.czy.seed.mvc.wbm.config.entity.type.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightTypeConfigTest {

    @Autowired
    private FlightTypeConfigController typeConfigController;

    @Test
    public void testQueryFlightTypeList() {
        List<AboutIndexConfigBean> icAndPcList = new ArrayList<>();

        AboutIndexConfigBean icAndPc = new AboutIndexConfigBean();
        PassengerCabin passengerCabin = new PassengerCabin();
        passengerCabin.setAdultNum(130);
        passengerCabin.setChildNum(23);
        passengerCabin.setInfantNum(32);
        icAndPc.setCabin(passengerCabin);
        List<IndexConfig> indexConfigList = new ArrayList<>();

        IndexConfig indexConfig = new IndexConfig();
        indexConfig.setId(1L);
        indexConfig.setTypes(1);
        indexConfig.setAircraftCabinId(3L);
        indexConfig.setWeight(new BigDecimal(234));
        indexConfig.setIndes(new BigDecimal(123));
        indexConfigList.add(indexConfig);
        icAndPc.setIndexConfigList(indexConfigList);
        icAndPcList.add(icAndPc);

        List<AboutIndexConfigBean> icAndChList = new ArrayList<>();
        List<IndexConfig> fuelList = new ArrayList<>();
        List<Crew> crewList = new ArrayList<>();
        List<Passenger> passengerList = new ArrayList<>();
        EchoConfBean confBean = typeConfigController.typeConfPacking(icAndPcList, icAndChList, fuelList, crewList, passengerList);

        Assert.assertEquals(confBean.getPcList(), icAndPcList);

    }


}
