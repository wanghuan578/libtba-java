package com.spirit.tsserialize.Exception;



public class TsException extends Exception {

    private ErrorType type;
    private String code;
    private String text;

    public TsException(String code, String text) {
        super(text);
        this.code = code;
        this.text = text;
    }
    public TsException(ErrorType type) {
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
