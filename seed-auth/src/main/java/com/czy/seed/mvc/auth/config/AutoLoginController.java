package com.czy.seed.mvc.auth.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("login")
public class AutoLoginController {
    @Resource(name="securityUtils")
    private SecurityUtils securityUtils;

    /**
     * OAuth 用户绑定本地用户
     * 然后自动登陆
     */
    @GetMapping("/bindingUser")
    public String bindingUser() {
        // [[1]] 绑定已有用户
        // [[2]] 绑定好后进行自动登陆
        String username = "QQ_admin";
        String password = "wrong"; // OAuth 授权的用户登陆不需要密码，因为不是在我们平台登陆的

        return "redirect:" + securityUtils.login(username, password);
    }

    /**
     * 不存在的用户登陆
     */
    @GetMapping("/nonExistingUserLogin")
    public String nonExistingUserLogin() {
        String username = "nonExistingUser";
        String password = "flash";

        return "redirect:" + securityUtils.login(username, password);
    }

    /**
     * admin 登陆
     */
    @GetMapping("/adminLogin")
    public String adminLogin() {
        String username = "admin";
        String password = "Passw0rd";

        return "redirect:" + securityUtils.login(username, password);
    }
}
