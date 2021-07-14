package com.spirit.tba.tools;

import com.spirit.tba.core.TbaRpcByteBuffer;
import com.spirit.tba.core.TbaRpcEventParser;
import com.spirit.tba.core.TbaRpcHead;

/**
 * @author wanghuan
 * @Date 2021/07/06 14:42
 * @licence all rights reserved
 */
public class TbaRpcMsgBuilder {
    private TbaRpcEventParser rpcEventParser;
    public TbaRpcMsgBuilder(TbaRpcByteBuffer msg) {
        rpcEventParser = new TbaRpcEventParser(msg);
    }

    public TbaRpcHead head() {
        return rpcEventParser.Head();
    }
}
