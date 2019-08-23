package com.spirit.tsserialize.Exception;


public enum ErrorType {

    SERIALIZE_EXCEPTION("0001", "序列化失败"),
    DESERIALIZE_EXCEPTION("0002", "反序列化失败"),
    WRITE_BUFFER_OUT_OF_RANGE_EXCEPTION("0003", "写入缓存越界"),
    UNEXPECTED_EXCEPTION("1000", "未知异常"),

    ;

    private String code;
    private String text;

    ErrorType(String code, String name) {
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
