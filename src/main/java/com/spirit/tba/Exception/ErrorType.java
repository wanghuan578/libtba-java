package com.spirit.tba.exception;

/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
public enum ErrorType {

    TERMINAL_PROCESS_EXCEPTION("0001", "terminal process exception"),
    DESERIALIZE_EXCEPTION("0002", "反序列化失败"),
    WRITE_BUFFER_OUT_OF_RANGE_EXCEPTION("0003", "写入缓存越界"),
    SHORT_HEAD_LENGTH_INVALID("0004", "magic头长度非法"),
    COMMON_HEAD_LENGTH_INVALID("0005", "协议头长度非法"),


    UNEXPECTED_EXCEPTION("1000", "未知异常"),
    USER_DEFINED_EXCEPTION("2000", "user defined exception"),
    ;

    private String code;
    private String text;

    private ErrorType(String code, String name) {
        this.code = code;
        this.text = name;
    }

    public String Code() {
        return code;
    }

    public ErrorType SetCode(String code) {
        this.code = code;
        return this;
    }
    public String Text() {
        return text;
    }
    public ErrorType SetText(String text) {
        this.text = text;
        return this;
    }
}
