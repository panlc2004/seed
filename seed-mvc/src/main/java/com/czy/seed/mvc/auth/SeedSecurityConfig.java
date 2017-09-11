package com.czy.seed.mvc.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;


@Configuration
@EnableConfigurationProperties
@ConditionalOnClass({EnableWebSecurity.class, AuthenticationEntryPoint.class})
@ConditionalOnMissingBean(WebSecurityConfiguration.class)
@ConditionalOnWebApplication
@EnableWebSecurity
public class SeedSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserDetailsService sysUserDetailsService;

//    private final String LOGIN_URL = "/auth/";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/j_spring_security_check").permitAll()
                .anyRequest().authenticated() //任何请求,登录后可以访问

                //登录配置
                .and().formLogin().loginPage("/login.html").loginProcessingUrl("/j_spring_security_check").permitAll()
                .successHandler(loginSuccessHandler())    ////登录成功后可使用loginSuccessHandler()存储用户信息
//                .failureHandler()

                //登出配置
                .and().logout().logoutUrl("/logout").and().logout().permitAll()
//                .addLogoutHandler()

                //异常配置
                .and().exceptionHandling().authenticationEntryPoint(ajaxAuthenticationEntryPoint())


//                .authorizeRequests().anyRequest().permitAll()

                //安全配置
                .and().csrf().disable()
                //允许页面加载在iframe中
                .headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("**/js/**", "**/css/**", "/lib/**", "/common/**",
                "/title.js");
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        指定密码加密所使用的加密器为passwordEncoder()
////        需要将密码加密后写入数据库
//        auth.userDetailsService(sysUserDetailsService).passwordEncoder(passwordEncoder());
//        auth.eraseCredentials(false);   //不删除凭据，以便记住用户
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint() {
        AjaxAuthenticationEntryPoint point = new AjaxAuthenticationEntryPoint("/login.html");
        return point;
    }

}
//
//
//http.authorizeRequests()
//        .anyRequest().authenticated()
//        .and().formLogin().loginPage("/login")
//        //设置默认登录成功跳转页面
//        .defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
//        .and()
//        //开启cookie保存用户数据
//        .rememberMe()
//        //设置cookie有效期
//        .tokenValiditySeconds(60 * 60 * 24 * 7)
//        //设置cookie的私钥
//        .key("")
//        .and()
//        .logout()
//        //默认注销行为为logout，可以通过下面的方式来修改
//        .logoutUrl("/custom-logout")
//        //设置注销成功后跳转页面，默认是跳转到登录页面
//        .logoutSuccessUrl("")
//        .permitAll();