package com.czy.seed.mvc.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by PLC on 2017/5/21.
 */


public class SysLoginController {

    public SysLoginController() {
        System.out.println(11212);
    }

    @RequestMapping("login")
    public String login() {
        return "success";
    }
}
