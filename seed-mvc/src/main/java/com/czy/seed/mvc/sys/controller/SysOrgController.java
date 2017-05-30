package com.czy.seed.mvc.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 组织机构管理
 * Created by PLC on 2017/5/30.
 */
@RestController
@RequestMapping("/sys/org")
public class SysOrgController {

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/sys/org-index");
        return mv;
    }
}
