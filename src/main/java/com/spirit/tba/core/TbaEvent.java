package com.spirit.tba.core;


public class TbaEvent extends TbaEventBase {

    private TsRpcHead head;
    private Object body;
    private Integer length;
    private Short encryptType;

    public TbaEvent(TsRpcHead head, Object body, int len, Short encrypt) {
        this.head = head;
        this.body = body;
        this.length = len;
        this.encryptType = encrypt;
    }

    public Short getEncryptType() {
        return encryptType;
    }

    public TbaEvent(TsRpcHead head, Object body) {
        this.head = head;
        this.body = body;
    }

    public TsRpcHead getHead() {
        return head;
    }

    public void setHead(TsRpcHead head) {
        this.head = head;
    }

    public Object getBody() {
        return body;
    }

    public void getBody(Object body) {
        this.body = body;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
