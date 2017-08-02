package com.czy.seed.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;

/**
 * Created by PLC on 2017/5/21.
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
