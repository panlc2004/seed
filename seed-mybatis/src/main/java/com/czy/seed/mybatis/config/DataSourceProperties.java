package com.czy.seed.mybatis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 003914[panlc] on 2017-07-25.
 */
@ConfigurationProperties(prefix = "datasource")
public class DataSourceProperties {

    private String pool;

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }
}
