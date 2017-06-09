package com.czy.seed.mvc.wbm.msg.service;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.config.entity.type.CabinConfig;

/**
 * Created by panlc on 2017-05-04.
 */
public interface MessageService {

    public String createMessage(FlightInfo flightInfo, CabinConfig cabinConfig);

}
