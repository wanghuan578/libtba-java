package com.spirit.tba.tools;

import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TsRpcByteBuffer;
import com.spirit.tba.core.TsRpcHead;

public class TbaHeadUtil {

    public static final int SIZE = 40;

    public static void build(TsRpcByteBuffer protcol, TsRpcHead head, int messageLength) throws TbaException {
        protcol.WriteBufferBegin(0);
        protcol.WriteI32(messageLength);
        protcol.WriteI16(head.GetFlag());
        protcol.WriteI16(head.GetType());
        protcol.WriteI32(head.GetSequence());
        protcol.WriteI32(head.GetSource());
        protcol.WriteI32(head.GetDestination());
        protcol.WriteI32(head.GetCheckSum());
        protcol.WriteI32(head.GetAttach1());
        protcol.WriteI32(head.GetAttach2());
        protcol.WriteI32(head.GetAttach3());
        protcol.WriteI32(head.GetAttach4());
    }

    public static int build_all(TsRpcByteBuffer protcol, TsRpcHead head) throws TbaException {
        int end = protcol.Length();
        build(protcol, head, end);
        protcol.WriteBufferBegin(end);
        return end;
    }
}
