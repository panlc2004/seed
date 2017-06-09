package com.czy.seed.mvc.wbm.math.formula.base;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;

/**
 * Created by panlc on 2017-04-24.
 */
public class Math {

    private FlightInfo flightInfo;

    public Math(FlightInfo flightInfo) {
        this.flightInfo = flightInfo;
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }
}
