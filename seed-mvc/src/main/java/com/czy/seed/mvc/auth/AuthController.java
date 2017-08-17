package com.czy.seed.mvc.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    /**
     * 登陆方法，被Spring拦截，不会被执行
     * @param username
     * @param password
     */
    @RequestMapping("/signin")
    public void signin(String username, String password) {

    }


}
