package com.example.demo.aspect;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 一段段伪代码读懂执行顺序
 * try {
    // @Before 执行前通知
    // 执行目标方法
    // @Around 执行环绕通知 成功走finall，失败走catch
} finally {
    // @After 执行后置通知
    // @AfterReturning 执行返回后通知
} catch(e) {
    // @AfterThrowing 抛出异常通知
}
 * @author Administrator
 *
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     *  定义一个公共的方法，实现服用
     *  拦截UserController下面的所有方法
     *  拦截UserController下面的userList方法里的任何参数(..表示拦截任何参数)写法：@Before("execution(public * com.angelo.controller.UserController.userList(..))")
     */
    @Pointcut("execution(public * com.example.demo.web.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("url", request.getRequestURL()); // 获取请求的url
        params.put("method", request.getMethod()); // 获取请求的方式
        params.put("ip", request.getRemoteAddr()); // 获取请求的ip地址
        params.put("className", joinPoint.getSignature().getDeclaringTypeName()); // 获取类名
        params.put("classMethod", joinPoint.getSignature().getName()); // 获取类方法
        params.put("args", joinPoint.getArgs()); // 请求参数

        // 输出格式化后的json字符串
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        logger.info("REQUEST: {}", gson.toJson(params));
    }

    @After("log()")
    public void doAfter() {
        logger.info("doAfter");
    }

    
    /**
     * 环绕通知 
     * 环绕通知是在方法的前后的一段逻辑操作,
     * 可以修改目标方法的返回值
     * @param point
     * @return
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint point) {
        try {
            Object o =  point.proceed();
            System.out.println("方法环绕proceed，结果是 :" + o);
            logger.info("doAround1");
            return o;
        } catch (Throwable e) {
            // e.printStackTrace();
            logger.info("doAround2");
            return null;
        }
    }
    
    /**
     * 获取响应返回值
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("RESPONSE: {}", object); // 会打印出一个对象，想打印出具体内容需要在定义模型处加上toString()
        logger.info("RESPONSE: {}", object.toString());
    }

    /**
     * 异常通知
     * 抛出异常后的通知，此时返回后通知@AfterReturning就不会执行。
     */
    @AfterThrowing(pointcut = "log()")
    public void doAfterThrowing() {
        logger.error("doAfterThrowing: {}", " 异常情况!");
    }
}
