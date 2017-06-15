/*
 * 文 件 名 : ResultMsg
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <返回结果封装对象>
 * 修 改 人 : <工号>xu.yang22@zte.com.cn
 * 修改时间 : 2017/5/31 16:34
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.entity.type;

import java.io.Serializable;

/**
 * <一句话功能简介><br>
 *
 * @author [011424]zhangyang@inner.czy.com
 * @version [版本号, 2017年6月9日]
 * @Description: 返回对象封装对象
 * @ClassName:ResultMsg
 * @see [相关类/方法]
 * @since [产品/模块]
 */
public class ResultMsg implements Serializable {
    /**
     * 返回数据对象
     */
    private Object msg;
    /**
     * 是否返回成功
     */
    private String success;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
