package com.example.demo.interceptor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.util.RedisUtil;

@Configuration
public class MonitorIntercetor implements HandlerInterceptor {
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 各个时间段的请求量 5分钟为一步
        countUri();
//        redisTemplate.executePipelined(new SessionCallback<T>() {
//
//            @Override
//            public <K, V> T execute(RedisOperations<K, V> operations) throws DataAccessException {
//                return null;
//            }
//        });
        return true;
    }
    private void countUri() {
        List<String> list  = (List<String>) redisUtil.get("uri-count");
        if(null == list) {
            list = new ArrayList<String>();
        }
        LocalDateTime now = LocalDateTime.now();
        list.add(now.toString());
        redisUtil.set("uri-count", list);
    }
    
}
