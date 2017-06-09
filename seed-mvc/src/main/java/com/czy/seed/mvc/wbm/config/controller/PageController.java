package com.czy.seed.mvc.wbm.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 * Created by Administrator on 2017/5/22.
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/info")
    public String info() {
        return "info";
    }


    @RequestMapping("/ch")
    public String ch() {
        return "ch";
    }

    @RequestMapping("/c")
    public String c() {
        return "c";
    }

    @RequestMapping("/gg")
    public String gg() {
        return "gg";
    }

    @RequestMapping("/os")
    public String os() {
        return "os";
    }

    @RequestMapping("/ftc")
    public String ftc() {
        return "ftc";
    }

    @RequestMapping("/pc")
    public String pc() {
        return "pc";
    }

    @RequestMapping("/uc")
    public String uc() {
        return "uc";
    }

    @RequestMapping("/ic")
    public String ic() {
        return "ic";
    }

    @RequestMapping("/p")
    public String p() {
        return "p";
    }

    @RequestMapping("/r")
    public String r() {
        return "r";
    }

    @RequestMapping("/u")
    public String u() {
        return "u";
    }

    @RequestMapping("/fc")
    public String fc() {
        return "fc";
    }


    @RequestMapping("/form")
    public String form() {
        return "form";
    }


    @RequestMapping("/cfg")
    public String cfg(){
        return "cfg";
    }



}
