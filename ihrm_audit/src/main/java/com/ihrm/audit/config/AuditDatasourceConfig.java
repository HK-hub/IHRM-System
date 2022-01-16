package com.ihrm.audit.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 *  多数据源配置类
 *      1.activiti数据库连接池
 *          默认
 *      2.IHRM业务数据库连接池
 *          明确指定 ihrmDataSource
 */
@Configuration
public class AuditDatasourceConfig extends AbstractProcessEngineAutoConfiguration {


    // activiti 数据源配置
    @Value("${spring.datasource.act.jdbc-url}")
    private String actUrl ;
    @Value("${spring.datasource.act.username}")
    private String actUsername ;
    @Value("${spring.datasource.act.password}")
    private String actPassword ;
    @Value("${spring.datasource.act.driver-class-name}")
    private String actDriverClassName ;


    // ihrm 数据源配置
    @Value("${spring.datasource.ihrm.jdbc-url}")
    private String ihrmUrl ;
    @Value("${spring.datasource.ihrm.username}")
    private String ihrmUsername ;
    @Value("${spring.datasource.ihrm.password}")
    private String ihrmPassword ;
    @Value("${spring.datasource.ihrm.driver-class-name}")
    private String ihrmDriverClassName ;


    @Bean
    @Primary       // 主数据源
    @ConfigurationProperties(prefix = "spring.datasource.act")
    @Qualifier("activitiDataSource")
    public DataSource activitiDataSource() {

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(actUrl).username(actUsername).password(actPassword).driverClassName(actDriverClassName).type(MysqlDataSource.class);

        return dataSourceBuilder.build();

        //return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ihrm")
    @Qualifier("ihrmDataSource")
    public DataSource ihrmDataSource() {

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(ihrmUrl).username(ihrmUsername).password(ihrmPassword).driverClassName(ihrmDriverClassName).type(MysqlDataSource.class);

        return dataSourceBuilder.build();


        //return DataSourceBuilder.create().build();
    }
}
