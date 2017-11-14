package com.czy.seed.mvc.auth;

import com.czy.seed.mvc.auth.captcha.CaptchaUtil;
import com.czy.seed.mvc.auth.constant.AuthConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void signin(String username, String password) {

    }


    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String verifyCode = CaptchaUtil.generateVerifyCode(4);
        request.getSession().setAttribute(AuthConstants.VERIFY_CODE,verifyCode.toLowerCase());
        int w = 200, h = 80;
        CaptchaUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}
