package com.czy.seed.mvc.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by PLC on 2017/5/21.
 */

@RestController
@RequestMapping("/sys/login")
public class SysLoginController {

    @RequestMapping("login")
    public String login() {
        return "success";
    }

    public String logout() {
        return "false";
    }
}
