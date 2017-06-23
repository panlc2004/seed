package com.czy.seed.mvc.auth;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 003914[panlc] on 2017-06-23.
 */
public class SeedSecurityFilter extends AbstractSecurityInterceptor implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }

//    @PostConstruct
//    public void init(){
//        super.setAuthenticationManager(authenticationManager);
//        super.setAccessDecisionManager(myAccessDecisionManager);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("filter===========================");
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        FilterInvocation fi = new FilterInvocation( request, response, chain );
//        invoke(fi);
//    }
//
//    @Override
//    public Class<?> getSecureObjectClass() {
//        return FilterInvocation.class;
//    }
//
//    @Override
//    public SecurityMetadataSource obtainSecurityMetadataSource() {
//        return this.mySecurityMetadataSource;
//    }
//
//    public void init( FilterConfig filterconfig ) throws ServletException{
//        System.out.println("filter===========================");
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("filter===========================end");
//    }
}
