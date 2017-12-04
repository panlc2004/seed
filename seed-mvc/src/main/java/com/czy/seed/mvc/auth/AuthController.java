package com.czy.seed.mvc.auth;

import com.czy.seed.mvc.auth.captcha.CaptchaUtil;
import com.czy.seed.mvc.auth.constant.AuthConstants;
import com.czy.seed.mvc.util.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登陆方法
     */
    @RequestMapping(value = AuthConstants.LOGIN_PAGE_URL, method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request) {
        String ajaxHeader = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
        if (isAjax) {
            return new ModelAndView(AuthConstants.LOGIN_AJAX_URL);
        } else {
            return new ModelAndView(AuthConstants.LOGIN_PAGE_HTML);
        }
    }

    /**
     * ajax登陆
     *
     * @returnGET
     */
    @RequestMapping(value = AuthConstants.LOGIN_AJAX_URL, method = RequestMethod.GET)
    @ResponseBody
    public Res loginAjax() {
        Map<String, Object> result = new HashMap<String, Object>();
        return Res.custom(401, "you need login!", null);
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String verifyCode = CaptchaUtil.generateVerifyCode(4);
        request.getSession().setAttribute(AuthConstants.VERIFY_CODE, verifyCode.toLowerCase());
        int w = 200, h = 80;
        CaptchaUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
    }


    @RequestMapping(value = AuthConstants.LOGIN_PROCESSING_URL, method = RequestMethod.POST)
    @ResponseBody
    public Res customLoginAction(@RequestParam(defaultValue = "") String username,
                                 @RequestParam(defaultValue = "") String password,
                                 HttpServletRequest request) {
        username = username.trim();
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            //返回登录页面
            return Res.error("username empty");
        }

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            // 这个非常重要，否则验证后将无法登陆
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }
        return Res.ok();
    }

}
