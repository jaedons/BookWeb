package com.example.demo.exception;

import com.example.demo.constant.MessageEnum;

/**
 *   自定义异常
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class ContextException extends RuntimeException {
    private Integer code;

    public ContextException(MessageEnum messageEnum) {
        super(messageEnum.getMessage());
        this.code = messageEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}