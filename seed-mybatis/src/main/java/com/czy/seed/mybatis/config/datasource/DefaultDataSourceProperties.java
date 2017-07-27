package com.czy.seed.mybatis.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by 003914[panlc] on 2017-07-25.
 */
@Configuration
@ConfigurationProperties(prefix = "default")
public class DefaultDataSourceProperties {

    private Map<String, Object> datasource;

    public Map<String, Object> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, Object> datasource) {
        this.datasource = datasource;
    }
}
