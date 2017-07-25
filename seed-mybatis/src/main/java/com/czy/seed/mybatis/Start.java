package com.czy.seed.mybatis;

import com.czy.seed.mybatis.tool.SpringContextHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by panlc on 2017-03-13.
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class })
@ComponentScan("com.czy.seed.mybatis")
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class);

        Object mybatisConfig = SpringContextHelper.getBeanById("mybatisConfig");
        System.out.println(mybatisConfig);

    }

}
