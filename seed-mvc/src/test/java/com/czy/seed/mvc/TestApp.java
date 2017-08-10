package com.czy.seed.mvc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class TestApp {
}
