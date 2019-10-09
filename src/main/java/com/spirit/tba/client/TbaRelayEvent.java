package com.spirit.tba.client;

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
