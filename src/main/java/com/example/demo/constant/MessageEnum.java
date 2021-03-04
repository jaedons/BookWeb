package com.example.demo.constant;

/**
 * 异常中的消息设置
 * @author Administrator
 *
 */
public enum MessageEnum {
    SYSTEM_ERROR(1001, "系统异常"),
    NAME_EXCEEDED_CHARRACTER_LIMIT(2000, "姓名超过了限制，最大4个字符!"),
    NOT_LOGIN(0000,"未登录");

    // 编码
    private Integer code;

    // 数据信息
    private String message;

    MessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
