package com.spirit.tba.core;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */

public class TbaEvent extends TbaEventBase {

    private TbaRpcHead head;
    private Object body;
    private Integer length;
    private TbaEncryptType encryptType;

    public TbaEvent(TbaRpcHead head, Object body, int len, TbaEncryptType encrypt) {
        this.head = head;
        this.body = body;
        this.length = len;
        this.encryptType = encrypt;
    }

    public TbaEncryptType getEncryptType() {
        return encryptType;
    }

    public TbaEvent(TbaRpcHead head, Object body) {
        this.head = head;
        this.body = body;
    }

    public TbaRpcHead getHead() {
        return head;
    }

    public void setHead(TbaRpcHead head) {
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
