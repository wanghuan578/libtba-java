package com.spirit.tsserialize.core;


public class TsEvent {

    private TsRpcHead head;
    private Object body;
    private Integer len;

    public TsEvent(TsRpcHead head, Object body, int len) {
        this.head = head;
        this.body = body;
        this.len = len;
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

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }
}
