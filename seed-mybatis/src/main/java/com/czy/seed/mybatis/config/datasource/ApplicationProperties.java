package com.czy.seed.mybatis.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 003914[panlc] on 2017-07-27.
 */
@Configuration
@ConfigurationProperties
public class ApplicationProperties {
    private String dataSourcePoolType;
    private String defaultDatasourceId;
    private String automapperLocations;
    private String mybatisMapperGenPath;
    private String mybatisMapperLocations;
    private String idGenModel;

    public String getDataSourcePoolType() {
        return dataSourcePoolType;
    }

    public void setDataSourcePoolType(String dataSourcePoolType) {
        this.dataSourcePoolType = dataSourcePoolType;
    }

    public String getDefaultDatasourceId() {
        return defaultDatasourceId;
    }

    public void setDefaultDatasourceId(String defaultDatasourceId) {
        this.defaultDatasourceId = defaultDatasourceId;
    }

    public String getAutomapperLocations() {
        return automapperLocations;
    }

    public void setAutomapperLocations(String automapperLocations) {
        this.automapperLocations = automapperLocations;
    }

    public String getMybatisMapperGenPath() {
        return mybatisMapperGenPath;
    }

    public void setMybatisMapperGenPath(String mybatisMapperGenPath) {
        this.mybatisMapperGenPath = mybatisMapperGenPath;
    }

    public String getMybatisMapperLocations() {
        return mybatisMapperLocations;
    }

    public void setMybatisMapperLocations(String mybatisMapperLocations) {
        this.mybatisMapperLocations = mybatisMapperLocations;
    }

    public String getIdGenModel() {
        return idGenModel;
    }

    public void setIdGenModel(String idGenModel) {
        this.idGenModel = idGenModel;
    }
}
