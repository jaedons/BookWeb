package com.example.demo.exception;

import com.example.demo.constant.MessageEnum;

@SuppressWarnings("serial")
public class ServiceException extends ContextException {

    public ServiceException(MessageEnum messageEnum) {
        super(messageEnum);
    }

}
