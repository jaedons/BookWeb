package com.example.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class CustomListenner implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(CustomListenner.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("servletContextListener 创建……");
        ServletContextListener.super.contextInitialized(sce);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("servletContextListener 销毁……");
        ServletContextListener.super.contextDestroyed(sce);
    }
}
