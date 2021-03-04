package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.convert.DateConverter;
import com.example.demo.interceptor.CustomInterceptor;
import com.example.demo.interceptor.MonitorIntercetor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    
    @Autowired
    private MonitorIntercetor monitorIntercetor;
    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("在执行 addInterceptor 方法");
        // addPathPatterns 添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(customInterceptor()).addPathPatterns("/**").excludePathPatterns("");
        registry.addInterceptor(monitorIntercetor).addPathPatterns("/**").excludePathPatterns("");
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
//        WebMvcConfigurer.super.addFormatters(registry);
    }
}
