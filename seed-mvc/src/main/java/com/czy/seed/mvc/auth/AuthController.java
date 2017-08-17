package com.czy.seed.mvc.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * 登陆方法，被Spring拦截，不会被执行
     *
     * @param username
     * @param password
     */
    @RequestMapping("/login")
    public void signin123(String username, String password) {

    }

    @RequestMapping("/test2/test3")
    public void test2() {
        System.out.println(123123);
    }

}
