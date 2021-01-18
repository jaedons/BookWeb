package com.example.demo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *   session 的 动态 监听
 * @author Administrator
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    
    private final Logger logger = LoggerFactory.getLogger(SessionListener.class); 
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("session创建成功");
        HttpSessionListener.super.sessionCreated(se);
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("session销毁");
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
