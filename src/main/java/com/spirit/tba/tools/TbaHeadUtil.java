package com.spirit.tba.tools;

import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TsHeadMagic;
import com.spirit.tba.core.TsRpcByteBuffer;
import com.spirit.tba.core.TsRpcHead;
import static com.spirit.tba.Exception.ErrorType.COMMON_HEAD_LENGTH_INVALID;
import static com.spirit.tba.Exception.ErrorType.SHORT_HEAD_LENGTH_INVALID;

public class TbaHeadUtil {

    public static final int HEAD_SIZE = 40;

    public static TsHeadMagic preParser(byte[] data) throws TbaException {
        if (data.length != TsHeadMagic.MAGIC_OFFSET) {
            throw new TbaException(SHORT_HEAD_LENGTH_INVALID);
        }
        TsRpcByteBuffer bb = new TsRpcByteBuffer(data, TsHeadMagic.MAGIC_OFFSET);
        return preParser(bb);
    }

    public static TsRpcHead parser(byte[] data) throws TbaException {
        if (data.length != HEAD_SIZE) {
            throw new TbaException(COMMON_HEAD_LENGTH_INVALID);
        }
        TsRpcByteBuffer bb = new TsRpcByteBuffer(data, HEAD_SIZE);
        return parser(bb);
    }

    public static TsHeadMagic preParser(TsRpcByteBuffer buff) {
        TsHeadMagic head = new TsHeadMagic();
        head.setLength(buff.ReadI32());
        head.setFlag(buff.ReadI16());
        head.setType(buff.ReadI16());
        return head;
    }

    public static TsRpcHead parser(TsRpcByteBuffer buff) {
        TsRpcHead head = new TsRpcHead();
        head.SetLength(buff.ReadI32());
        head.SetFlag(buff.ReadI16());
        head.SetType(buff.ReadI16());
        head.SetSequence(buff.ReadI32());
        head.SetSource(buff.ReadI32());
        head.SetDestination(buff.ReadI32());
        head.SetCheckSum(buff.ReadI32());
        head.SetAttach1(buff.ReadI32());
        head.SetAttach2(buff.ReadI32());
        head.SetAttach3(buff.ReadI32());
        head.SetAttach4(buff.ReadI32());
        return head;
    }

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
