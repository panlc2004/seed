package com.czy.seed.mvc.auth;

import com.czy.seed.mvc.auth.captcha.CaptchaUtil;
import com.czy.seed.mvc.auth.constant.AuthConstants;
import com.czy.seed.mvc.util.Res;
import com.czy.seed.mybatis.tool.NullUtil;
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
        return Res.custom(401, "请先登陆系统", null);
    }

    /**
     * 验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String verifyCode = CaptchaUtil.generateVerifyCode(4);
        request.getSession().setAttribute(AuthConstants.VERIFY_CODE, verifyCode.toLowerCase());
        request.getSession().setAttribute(AuthConstants.VERIFY_CODE_GEN_TIME, System.currentTimeMillis());
        int w = 100, h = 40;
        CaptchaUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /**
     * 校验验证码
     *
     * @param verifyCode
     * @param cachedVerifyCode
     * @param verifyCodeGenTime
     * @return
     */
    private CaptchaValidateInfo captchaIsRight(String verifyCode, String cachedVerifyCode, Long verifyCodeGenTime) {
        CaptchaValidateInfo res = new CaptchaValidateInfo(true, "");
        String error = "验证码错误";
        String timeout = "验证码过期";
        //校验验证码
        if (NullUtil.isEmpty(verifyCode)) {
            res = new CaptchaValidateInfo(false, error);
        }
        //校验内容是否正确
        else if (!verifyCode.equalsIgnoreCase(cachedVerifyCode)) {
            res = new CaptchaValidateInfo(false, error);
        } else {
            //校验时间是否过期：30秒
            Long now = System.currentTimeMillis();
            //超时时间
            int checkLimit = 30000;
            if (now - verifyCodeGenTime > checkLimit) {
                res = new CaptchaValidateInfo(false, timeout);
            }
        }
        return res;
    }

    @RequestMapping(value = AuthConstants.LOGIN_PROCESSING_URL, method = RequestMethod.POST)
    @ResponseBody
    public Res customLoginAction(@RequestParam(defaultValue = "") String username,
                                 @RequestParam(defaultValue = "") String password,
                                 @RequestParam(defaultValue = "") String verifyCode,
                                 HttpServletRequest request) {
        //校验验证码
        HttpSession session = request.getSession();
        String cachedVerifyCode = session.getAttribute(AuthConstants.VERIFY_CODE).toString();
        Long verifyCodeGenTime = (Long) session.getAttribute(AuthConstants.VERIFY_CODE_GEN_TIME);
        CaptchaValidateInfo captcha = captchaIsRight(verifyCode, cachedVerifyCode, verifyCodeGenTime);
        if (!captcha.isValidate()) {
            return Res.custom(401, captcha.getMsg(), null);
        }

        //校验账号、密码
        username = username.trim();
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            //返回登录页面
            return Res.error("用户名不能为空");
        }
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 这个非常重要，否则验证后将无法登陆
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        } catch (AuthenticationException ex) {
            return Res.custom(401, "账号或密码错误", null);
        }
        return Res.ok();
    }

    /**
     * 校验信息封装类
     */
    class CaptchaValidateInfo {

        public CaptchaValidateInfo(boolean validate, String msg) {
            this.validate = validate;
            this.msg = msg;
        }

        /**
         * 是否校验通过
         */
        private boolean validate;
        /**
         * 校验描述信息
         */
        private String msg;

        public boolean isValidate() {
            return validate;
        }

        public String getMsg() {
            return msg;
        }
    }

}
