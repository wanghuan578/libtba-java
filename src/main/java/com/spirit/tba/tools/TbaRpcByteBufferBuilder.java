package com.spirit.tba.tools;

import com.spirit.tba.core.TbaRpcByteBuffer;
import com.spirit.tba.exception.TbaException;


public class TbaRpcByteBufferBuilder {

    public static TbaRpcByteBuffer build(int len) throws TbaException {
        TbaRpcByteBuffer msg = new TbaRpcByteBuffer(len);
        msg.writeI32(len);
        return msg;
    }
}
