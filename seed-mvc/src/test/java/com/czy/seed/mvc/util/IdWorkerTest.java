/*
 * 文 件 名 : IdWorkerTest
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <描述>
 * 修 改 人 : <工号>xu.yang22@zte.com.cn
 * 修改时间 : 2017/6/16 15:21
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.util;

import com.czy.seed.mybatis.util.IdWorker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class IdWorkerTest {

    @Test
    public void testIdWorker() {
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
    }

}
