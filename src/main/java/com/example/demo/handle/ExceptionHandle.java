package com.example.demo.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Result;
import com.example.demo.exception.ContextException;
import com.example.demo.util.MessageUtil;

/**
 * 统一异常处理
 * @author Administrator
 *
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> Result<T> handle(Exception e) {
        logger.info("进入error");

        // 是否属于自定义异常
        if (e instanceof ContextException) {
            ContextException contextException = (ContextException) e;

            return MessageUtil.error(contextException.getCode(), contextException.getMessage());
        } else {
            logger.error("系统异常 {}", e);
            return MessageUtil.error(MessageUtil.EROOR_CODE2, "系统异常!");
        }
    }
}
