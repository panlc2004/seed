package com.czy.seed.mvc.wbm.math.factory;

import com.czy.seed.mvc.wbm.config.entity.flight.FlightInfo;
import com.czy.seed.mvc.wbm.math.formula.base.DoMath;
import com.czy.seed.mvc.wbm.math.formula.base.LdMath;
import com.czy.seed.mvc.wbm.math.formula.base.Math;

/**
 * Created by panlc on 2017-04-24.
 */
public class MathResultFactory {

    public static Math getMathResult(FlightInfo flightInfo) {
        return new LdMath(flightInfo);
    }

    public static Math getDoMath(FlightInfo flightInfo) {
        return new DoMath(flightInfo);
    }

}
