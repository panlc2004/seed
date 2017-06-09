package com.czy.seed.mvc.conf.log;

import java.lang.annotation.*;

/**
 * 自动记录系统日志注解
 * Created by 003914[panlc] on 2017-06-09.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {
}
