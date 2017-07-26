package com.czy.seed.mybatis;

import com.czy.seed.mybatis.tool.SpringContextHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by panlc on 2017-03-13.
 */
@Configuration
@ComponentScan("com.czy.seed.mybatis")
@ImportResource("classpath:core/applicationContext-core.xml")
public class MybatisStarter {

    public static void main(String[] args) {
        SpringApplication.run(MybatisStarter.class);

        Object mybatisConfig = SpringContextHelper.getBeanById("mybatisConfig");
        System.out.println(mybatisConfig);

    }

}
