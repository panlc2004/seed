/*
 * 文 件 名 : ConfigBalanceController
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <描述>
 * 修 改 人 : <011424>zhangyang@inner.czy.com
 * 修改时间 : 2017/6/10 14:13
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */
package com.czy.seed.mvc.wbm.config.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
/**
 * <一句话功能简介><br>
 *
 * @author [011424]zhangyang@inner.czy.com
 * @version [版本号, 2017年6月9日]
 * @Description: <配载平衡页面跳转控制器>
 * @ClassName:CargoHoldController
 * @see [相关类/方法]
 * @since [产品/模块]
 */
@RestController
@RequestMapping("/wbm/cfg")
public class ConfigBalanceController {

    /**
     * 类型配载页
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/wbm/cfg-type");
        return mv;
    }


    /**
     * 架次参数配载页
     *
     * @return
     */
    @RequestMapping("/info")
    public ModelAndView info() {
        ModelAndView mv = new ModelAndView("/wbm/cfg-info");
        return mv;
    }
    /**
     * 电子舱单配置页
     *
     * @return
     */
    @RequestMapping("/manifest")
    public ModelAndView manifest() {
        ModelAndView mv = new ModelAndView("/wbm/manifest");
        return mv;
    }


}
