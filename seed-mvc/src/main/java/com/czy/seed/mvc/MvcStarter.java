package com.czy.seed.mvc;

import com.czy.seed.mvc.conf.SeedConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.czy.seed.mvc")
@EnableConfigurationProperties(SeedConfigProperties.class)
public class MvcStarter {
}
