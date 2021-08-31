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
@MapperScan(basePackages = "com.example.demo.mapper.mqtt",sqlSessionTemplateRef = "mqttSqlSessionTemplate")
public class MqttDataSourceConfig {

    /** mqtt数据库数据源配置 */
    @Bean(name="mqttDataSourceProperties") //mqttSqlSessionTemplate
    @ConfigurationProperties(prefix = "spring.datasource.mqtt")
    public DataSourceProperties mqttDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    /**  mqtt数据库数据源 */
    @Bean(name="mqttDataSource")
    public DataSource mqttDataSource(@Qualifier("mqttDataSourceProperties")DataSourceProperties mqttDataSourceProperties) {
        return mqttDataSourceProperties.initializeDataSourceBuilder().build();
    } 
    @Bean("mqttSqlSessionFactory")
    public SqlSessionFactory mqttSqlSessionFactory(@Qualifier("mqttDataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        String locationPattern = "classpath*:mybatis/mysql/mapper/mqtt/*.xml";
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(locationPattern));
        return sqlSessionFactory.getObject();
    }
    
    @Bean("mqttTransactionManager")
    public DataSourceTransactionManager mqttTransactionManager(@Qualifier("mqttDataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean(name="mqttSqlSessionTemplate")
    public SqlSessionTemplate mqttSqlSessionTemplate(@Qualifier("mqttSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
