package com.czy.seed.mybatis.config;

import com.czy.seed.mybatis.config.datasource.*;
import com.czy.seed.mybatis.config.mybatis.MybatisAtomikosConfig;
import com.czy.seed.mybatis.config.mybatis.MybatisConfig;
import com.czy.seed.mybatis.tool.SpringContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by PLC on 2017/5/3.
 */
@Configuration
@EnableConfigurationProperties({DefaultDataSourceProperties.class, DynamicDataSourceProperties.class,
        DataSourceProperties.class, ApplicationProperties.class})
public class DataBaseEnvInit {

    @Autowired
    private SpringContextHelper springContextHelper;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    /**
     * 根据配置的数据源类型，初始化不同的数据源和mybatis环境
     */
    @PostConstruct
    public void initDataSource() {
        String datasourcePool = dataSourceProperties.getPool();
        boolean initNormalEvnFlag = true;   //是否初始化普通数据源
        if (datasourcePool != null && datasourcePool.startsWith("atomikos")) {
            initNormalEvnFlag = false;
        }
        //如果配置了动态数据源，则自动使用atomikos数据连接
//        Map<String, Object> ctxPropertiesMap = SpringPropertiesUtil.getCtxPropertiesMap();
//        for (String key : ctxPropertiesMap.keySet()) {
//            if (key.startsWith(DataSourceBuilder.DYNAMIC_DATASOURCE_PREFIX)) {
//                initNormalEvnFlag = false;
//                break;
//            }
//        }

        if (initNormalEvnFlag) {
            initNormalEnv();
        } else {
            initAtomikosEnv();
        }
    }

    /**
     * 初始化atomikos数据源
     */
    private void initAtomikosEnv() {
        springContextHelper.addBean(AtomikosDataSourceBuilder.class,
                "dataSourceBuilder", null,
                "calPoolType", null);
        //初始化Mybatis
        springContextHelper.addBean(MybatisAtomikosConfig.class,
                "mybatisConfig", null,
                "registerTransactionManager", null);
    }

    /**
     * 初始化普通数据源
     */
    private void initNormalEnv() {
        springContextHelper.addBean(DataSourceBuilder.class,
                "dataSourceBuilder", null,
                "calPoolType", null);
        //初始化Mybatis
        springContextHelper.addBean(MybatisConfig.class,
                "mybatisConfig", null,
                "registerTransactionManager", null);
    }

}
