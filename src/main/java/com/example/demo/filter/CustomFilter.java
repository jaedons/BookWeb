package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.core.config.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "customFilter",urlPatterns = "/")
@Order(value = 1)
public class CustomFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(CustomFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("在执行 {} 方法"," doFilter");
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("在执行 {} 方法"," init");
        Filter.super.init(filterConfig);
    }
    
    @Override
    public void destroy() {
        logger.info("在执行 {} 方法"," destroy");
        Filter.super.destroy();
    }

}
