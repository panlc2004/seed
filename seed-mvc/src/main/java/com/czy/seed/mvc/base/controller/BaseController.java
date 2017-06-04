package com.czy.seed.mvc.base.controller;

import com.czy.seed.mvc.base.service.BaseService;
import com.czy.seed.mvc.sys.entity.SysUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by PLC on 2017/5/21.
 */
public class BaseController<T> {

    private BaseService<T> service;

}
