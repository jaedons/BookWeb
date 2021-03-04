package com.example.demo.exception;

import com.example.demo.constant.MessageEnum;

@SuppressWarnings("serial")
public class DaoException extends ContextException {

    public DaoException(MessageEnum messageEnum) {
        super(messageEnum);
    }

}
