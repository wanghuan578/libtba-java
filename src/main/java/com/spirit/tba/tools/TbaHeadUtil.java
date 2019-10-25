package com.spirit.tba.tools;

import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TsMagic;
import com.spirit.tba.core.TsRpcByteBuffer;
import com.spirit.tba.core.TsRpcHead;
import static com.spirit.tba.Exception.ErrorType.COMMON_HEAD_LENGTH_INVALID;
import static com.spirit.tba.Exception.ErrorType.SHORT_HEAD_LENGTH_INVALID;

public class TbaHeadUtil {

    public static final int HEAD_SIZE = 40;

    public static TsMagic preParser(byte[] data) throws TbaException {
        if (data.length != TsMagic.MAGIC_OFFSET) {
            throw new TbaException(SHORT_HEAD_LENGTH_INVALID);
        }
        TsRpcByteBuffer bb = new TsRpcByteBuffer(data, TsMagic.MAGIC_OFFSET);
        return preParser(bb);
    }

    public static TsRpcHead parser(byte[] data) throws TbaException {
        if (data.length != HEAD_SIZE) {
            throw new TbaException(COMMON_HEAD_LENGTH_INVALID);
        }
        TsRpcByteBuffer bb = new TsRpcByteBuffer(data, HEAD_SIZE);
        return parser(bb);
    }

    public static TsMagic preParser(TsRpcByteBuffer buff) {
        TsMagic magic = new TsMagic();
        magic.setLength(buff.ReadI32());
        magic.setFlag(buff.ReadI16());
        return magic;
    }

    public static TsRpcHead parser(TsRpcByteBuffer buff) {
        TsRpcHead head = new TsRpcHead();
        head.setLength(buff.ReadI32());
        head.setFlag(buff.ReadI16());
        head.setType(buff.ReadI16());
        head.setSequence(buff.ReadI32());
        head.setSource(buff.ReadI32());
        head.setDestination(buff.ReadI32());
        head.setChecksum(buff.ReadI32());
        head.setAttachId1(buff.ReadI32());
        head.setAttachId2(buff.ReadI32());
        head.setAttachId3(buff.ReadI32());
        head.setAttachId4(buff.ReadI32());
        return head;
    }

    public static void build(TsRpcByteBuffer protcol, TsRpcHead head, int messageLength) throws TbaException {
        protcol.writeBufferBegin(0);
        protcol.writeI32(messageLength);
        protcol.writeI16(head.getFlag());
        protcol.writeI16(head.getType());
        protcol.writeI32(head.getSequence());
        protcol.writeI32(head.getSource());
        protcol.writeI32(head.getDestination());
        protcol.writeI32(head.getChecksum());
        protcol.writeI32(head.getAttachId1());
        protcol.writeI32(head.getAttachId2());
        protcol.writeI32(head.getAttachId3());
        protcol.writeI32(head.getAttachId4());
    }

    public static int build_all(TsRpcByteBuffer protcol, TsRpcHead head) throws TbaException {
        int end = protcol.length();
        build(protcol, head, end);
        protcol.writeBufferBegin(end);
        return end;
    }
}
