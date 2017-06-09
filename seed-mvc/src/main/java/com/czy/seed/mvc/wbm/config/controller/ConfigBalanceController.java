package com.czy.seed.mvc.wbm.config.controller;/*
 * 文 件 名 : ConfigBalanceController
 * 版    权 : CZYSOFT TECHNOLOGY CO.,LTD.Copyright 2017-2030.All rights reserved
 * 描    述 : <描述>
 * 修 改 人 : <工号>xu.yang22@zte.com.cn
 * 修改时间 : 2017/6/1 16:40
 * 需求单号 : <需求Redmine单号>
 * 变更单号 : <变更Redmine单号>
 * 修改内容 : <修改内容>
 * Version : V1.0
 */


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
     * 类型配载列表页
     *
     * @return
     */
    @RequestMapping("/confTableList")
    public ModelAndView confTableList() {
        ModelAndView mv = new ModelAndView("/wbm/flight-cfg");
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
     * 从航班类型列表选取一条数据进行跳转到对应的列表页面中
     *
     * @param flightTypeConfigId 航班类型主键ID
     * @return
     */
    @RequestMapping("/turnTypeConfig")
    public ModelAndView turnTypeConfig(@RequestParam Long flightTypeConfigId) {
        ModelAndView mv = new ModelAndView("/wbm/cfg-type");
        mv.addObject("flightTypeConfigId", flightTypeConfigId);
        return mv;
    }


}
