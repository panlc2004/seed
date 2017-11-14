package com.czy.seed.mvc.auth;

import com.czy.seed.mvc.auth.constant.AuthConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 003914[panlc] on 2017-11-14.
 */
public class SeedVerificationCodeFilter extends UsernamePasswordAuthenticationFilter {

    public SeedVerificationCodeFilter() {
        AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/j_spring_security_check", "POST");
        this.setRequiresAuthenticationRequestMatcher(requestMatcher);
//        this.setAuthenticationManager(authenticationManager);
//        this.setAuthenticationFailureHandler(new LoginAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String verification = request.getParameter(AuthConstants.VERIFY_CODE);
        String captcha = (String) request.getSession().getAttribute(AuthConstants.VERIFY_CODE);
        if (captcha == null) {
            throw new AuthenticationServiceException("验证码错误");
        }
        if (captcha != null && !captcha.contentEquals(verification)) {
            throw new AuthenticationServiceException("验证码错误");
        }
        return super.attemptAuthentication(request, response);
    }

}
