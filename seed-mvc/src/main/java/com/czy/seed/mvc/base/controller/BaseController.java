package com.czy.seed.mvc.base.controller;

import com.czy.seed.mvc.base.exception.IllegalleControllerNameException;
import com.czy.seed.mvc.base.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by PLC on 2017/5/21.
 */
public class BaseController<T> {

    private BaseService<T> service;

    @RequestMapping("/index")
    public ModelAndView index() {
        String className = this.getClass().getSimpleName();
        String pageName = getPageName(className);
        return new ModelAndView(pageName);
    }

    /**
     * 获取页面名
     * @param className 类名
     * @return
     */
    private String getPageName(String className) {
        for (int i = 0; i < className.length(); i++) {
            if (Character.isUpperCase(className.charAt(i)) == true) {
                return className.substring(i, className.length() - 10).toLowerCase();
            }
        }
        throw new IllegalleControllerNameException();
    }

}
