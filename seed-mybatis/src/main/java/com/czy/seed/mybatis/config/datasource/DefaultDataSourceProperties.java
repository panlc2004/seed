package com.czy.seed.mybatis.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Created by 003914[panlc] on 2017-07-25.
 */
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
