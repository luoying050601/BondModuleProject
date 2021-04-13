package com.cscec8b.bond.module.config;


import com.cscec8b.common.module.config.dataSourceConfig.StmDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by chenzhibin on 2018/6/14.
 */
@Configuration
@MapperScan(basePackages = {"com.cscec8b.common.module.dao.stmDao", "com.cscec8b.bond.module.dao"},
        sqlSessionTemplateRef = "sqlSessionTemplate_one")
public class BondStmDataSourceConfig extends StmDataSourceConfig {


}
