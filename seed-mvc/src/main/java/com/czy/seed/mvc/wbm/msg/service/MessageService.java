package com.czy.seed.mvc.wbm.msg.service;

import com.czy.seed.mvc.wbm.config.entity.Manifest;
import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.CabinConfig;
import com.czy.seed.mvc.wbm.msg.flight.SheetMessage;

/**
 * Created by panlc on 2017-05-04.
 */
public interface MessageService {

    public String createMessage(FlightInfo flightInfo, CabinConfig cabinConfig);

    /**
     * 电子舱单生成
     * @param flightInfo 航班信息
     * @param manifest 电子舱单回传数据对象
     * @return
     */
    public String createMessage(FlightInfo flightInfo, Manifest manifest, SheetMessage sheetMassage);

}
