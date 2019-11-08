package com.spirit.tba.client;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.core.TbaEventBase;

public class TbaRelayEvent extends TbaEventBase {
    private byte[] relayMsg = null;

    public TbaRelayEvent(byte[] relayMsg) {
        this.relayMsg = relayMsg;
    }

    public byte[] getRelayMsg() {
        return relayMsg;
    }

    public void setRelayMsg(byte[] relayMsg) {
        this.relayMsg = relayMsg;
    }
}
