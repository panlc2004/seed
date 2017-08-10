package com.czy.seed.mvc.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 权限认证
 * Created by panlc on 2017-05-22.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserDetailsService sysUserDetailsService;
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/public/**", "/**/*.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login/**").permitAll() // ajax登录
            .antMatchers("/j_spring_security_check", "/csrf").permitAll() // spring security默认地址
            .anyRequest().authenticated() // 其他地址的访问均需验证权限
            .and().formLogin()
                    .loginPage("/login.html") // 登录页面，未登录时会自动跳转到登录页面
                    .usernameParameter("username").passwordParameter("password")
                    .loginProcessingUrl("/j_spring_security_check") // 这个controller不会执行（会被security的登录拦截器拦截）
                    .defaultSuccessUrl("/index.html", false) // 登录拦截器在成功登录时跳转的路径
                    .failureUrl("/login.html?error") // 登录拦截器登录失败（密码错误等）会跳转到此页面
                    .permitAll()
                    //.successHandler(loginSuccessHandler())
            .and().logout()
                    .logoutUrl("logout") // spring security默认地址
                    .logoutSuccessUrl("/login.html?logout")
                    .invalidateHttpSession(true)
                    //.addLogoutHandler(logoutHandler())
                    .permitAll()
            .and().csrf().disable().headers().frameOptions().disable();
    }

    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                    .withUser("admin").password("admin").roles("ADMIN", "USER").and()
//                    .withUser("user").password("user").roles("USER");
          auth.userDetailsService(sysUserDetailsService);//.passwordEncoder(passwordEncoder());
          auth.authenticationProvider(myAuthenticationProvider());
          auth.eraseCredentials(false);   //不删除凭据，以便记住用户
          // SocialAuthenticationFilter
    }
    
    @Bean
    public MyAuthenticationProvider myAuthenticationProvider() {
        MyAuthenticationProvider ret = new MyAuthenticationProvider();
        ret.setUserDetailsService(sysUserDetailsService);
        ret.setPasswordEncoder(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder());
        return ret;
    }

    @Bean
    public AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint() {
        AjaxAuthenticationEntryPoint point = new AjaxAuthenticationEntryPoint("/login.html");
        return point;
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