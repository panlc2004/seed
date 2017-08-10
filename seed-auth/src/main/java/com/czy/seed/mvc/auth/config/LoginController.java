package com.czy.seed.mvc.auth.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {
    @Resource(name="securityUtils")
    private SecurityUtils securityUtils;
    
    // spring security 登录标识
    @RequestMapping("/j_spring_security_check")
    public void jssc() {
        // do nothing, this method will never be excuted.
    }
    
    // spring security 退出标识
    @RequestMapping("/logout")
    public void logout() {
        // do nothing, this method will never be excuted.
    }
    
    // 退出登录
    @RequestMapping("/signout")
    public String signout(HttpServletRequest request) {
        return "redirect:" + securityUtils.logout(request);
    }
    
    @GetMapping("/home")
    public String home(Map<String, Object> model) {
        model.put("title", "Spring Security Hello World");
        model.put("message", "This is protected page!");
        model.put("date", new Date());
        
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.put("username", userDetails.getUsername());
        
        System.out.println("-----------------------------------------------home");
        return "home";
    }
    
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("-----------------------------------------------prepareTransactionManager");
        return "terst";
    }
    
//    @RequestMapping("/logout")
//    public String logout() {
//        System.out.println("-----------------------------------------------logout");
//        return "redirect:" + "/prepareTransactionManager.html";
//    }

//    @GetMapping(value="/login")
//    public String loginPage(@RequestParam(value="error", required=false) String error,
//                            @RequestParam(value="logout", required=false) String logout,
//                            ModelMap model) {
//        if (error != null) {
//            model.put("error", "Username or password is not correct");
//        } else if (logout != null) {
//            model.put("logout", "Logout successful");
//        }
//
//        return "login.fm";
//    }
//
//    @GetMapping("/deny")
//    @ResponseBody
//    public String denyPage() {
//        return "You have no permission to access the page";
//    }
}
