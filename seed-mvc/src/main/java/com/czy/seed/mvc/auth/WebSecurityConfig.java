package com.czy.seed.mvc.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 权限认证
 * Created by panlc on 2017-05-22.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll() //无需权限
                .anyRequest().authenticated()   //其他所有访问均需要权限
                .and()
                .formLogin()
                .loginPage("/toLogin.html")     //指定登录页是"/login"
                .permitAll()
                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                .logout()
                .logoutSuccessUrl("/home") //退出登录后的默认网址是”/home”
                .permitAll()
                .invalidateHttpSession(true);
//                .and()
//                .rememberMe()                   //登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
//                .tokenValiditySeconds(1209600);


//        http.authorizeRequests()
//                .antMatchers("/script/**", "/pages/**").permitAll()
//                .and().formLogin().loginPage("/toLogin.html").successHandler(loginSuccessHandler()).permitAll();
//                .failureUrl("/login").failureHandler(authenticationFailureHandler).permitAll()
//                .and().logout().logoutSuccessUrl("/login").logoutSuccessHandler(logoutSuccessHandler).permitAll()
//                .and().exceptionHandling().accessDeniedPage("/sucurityError")
//                .and().csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("1").password("1").roles("USER");
        //指定密码加密所使用的加密器为passwordEncoder()
        //需要将密码加密后写入数据库
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    /**
     * 设置用户密码的加密方式为MD5加密
     *
     * @return
     */
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

}
