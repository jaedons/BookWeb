package com.example.demo.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.util.DateUtil;
import com.example.demo.util.RedisUtil;

/**
 *  自定义拦截器
 * @author Administrator
 *
 */
public class CustomInterceptor implements HandlerInterceptor {
    
    private final Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);
    
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info(" exceute preHandler ");
        setRedisDateByUri(request);
        return true; // false 
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    @SuppressWarnings("unchecked")
    private void setRedisDateByUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Map<String,List<String>> uriMap = (Map<String, List<String>>) redisUtil.get("uri-book");
        if(null == uriMap) {
            uriMap = new HashMap<String, List<String>>();
        }
        List<String> uriList = uriMap.get(uri);
        if(null == uriList) {
            uriList = new ArrayList<String>();
        }
        uriList.add(DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        uriMap.put(uri, uriList);
        redisUtil.set("uri-book", uriMap);
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        logger.info(" exceute postHandle ");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info(" exceute afterCompletion ");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
    
}
