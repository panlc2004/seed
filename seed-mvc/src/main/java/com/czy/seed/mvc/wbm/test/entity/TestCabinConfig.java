package com.czy.seed.mvc.wbm.test.entity;/*
 * 文 件 名 : TestCabinConfig
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <描述>
 * 修 改 人 : <工号>xu.yang22@zte.com.cn
 * 修改时间 : 2017/5/27 16:15
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */

import com.czy.seed.mvc.wbm.config.entity.type.CabinConfig;
import org.junit.Test;

import java.lang.reflect.Field;

public class TestCabinConfig {

    @Test
    public void testFlelds() {
        Field[] fields = CabinConfig.class.getDeclaredFields();
        Field.setAccessible(fields, true);

        for (Field field : fields) {
            System.out.println(field.getName());
        }

    }


}
