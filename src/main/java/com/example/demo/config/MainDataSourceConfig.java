package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.example.demo.mapper.main",sqlSessionTemplateRef = "mainSqlSessionTemplate")
public class MainDataSourceConfig {

    /** 主数据库s数据源配置 */
    @Primary
    @Bean(name="mainDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.main")
    public DataSourceProperties mainDataSourceProperties() {
        DataSourceProperties properties = new DataSourceProperties();
        return properties;
    }
    
    /** 主数据库源 */
    @Primary
    @Bean(name="mainDataSource")
    public DataSource mainDataSource(@Qualifier("mainDataSourceProperties")DataSourceProperties mainDataSourceProperties) {
        return mainDataSourceProperties.initializeDataSourceBuilder().build();
    } 
    
    @Primary
    @Bean("mainSqlSessionFactory")
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("mainDataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        String locationPattern = "classpath*:mybatis/mysql/mapper/main/*.xml";
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(locationPattern));
        return sqlSessionFactory.getObject();
    }
    
    @Primary
    @Bean("mainTransactionManager")
    public DataSourceTransactionManager mainTransactionManager(@Qualifier("mainDataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Primary
    @Bean(name="mainSqlSessionTemplate")
    public SqlSessionTemplate mainSqlSessionTemplate(@Qualifier("mainSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
