package com.spirit.tba.exception;

/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */

public class TbaException extends Exception {

    private ErrorType type;
    private String code;
    private String text;

    public TbaException(String code, String text) {
        super(text);
        this.code = code;
        this.text = text;
    }
    public TbaException(ErrorType type) {
        super(type.Text());
        this.type = type;
    }

    public ErrorType getResultType() {
        return type;
    }
    public String getCode() {
        return type != null ? type.Code() : code;
    }
    public String getText() {
        return type != null ? type.Text() : text;
    }
}
