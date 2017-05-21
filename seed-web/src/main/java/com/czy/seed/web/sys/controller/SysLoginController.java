package com.czy.seed.web.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PLC on 2017/5/21.
 */

@RequestMapping("/sys/login")
@RestController
public class SysLoginController {

    public SysLoginController() {
        System.out.println(1);
    }

    @RequestMapping("login")
    public String login() {
        return "success111";
    }
}
