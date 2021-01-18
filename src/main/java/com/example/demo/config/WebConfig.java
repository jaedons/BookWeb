package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.CustomInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("在执行 addInterceptor 方法");
        // addPathPatterns 添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("").excludePathPatterns("");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
