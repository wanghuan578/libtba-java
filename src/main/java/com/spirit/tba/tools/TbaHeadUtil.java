package com.spirit.tba.tools;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TbaMagic;
import com.spirit.tba.core.TbaRpcByteBuffer;
import com.spirit.tba.core.TbaRpcHead;
import static com.spirit.tba.Exception.ErrorType.COMMON_HEAD_LENGTH_INVALID;
import static com.spirit.tba.Exception.ErrorType.SHORT_HEAD_LENGTH_INVALID;

public class TbaHeadUtil {

    public static final int HEAD_SIZE = 40;

    public static TbaMagic preParser(byte[] data) throws TbaException {
        if (data.length != TbaMagic.MAGIC_OFFSET) {
            throw new TbaException(SHORT_HEAD_LENGTH_INVALID);
        }
        TbaRpcByteBuffer bb = new TbaRpcByteBuffer(data, TbaMagic.MAGIC_OFFSET);
        return preParser(bb);
    }

    public static TbaRpcHead parser(byte[] data) throws TbaException {
        if (data.length != HEAD_SIZE) {
            throw new TbaException(COMMON_HEAD_LENGTH_INVALID);
        }
        TbaRpcByteBuffer bb = new TbaRpcByteBuffer(data, HEAD_SIZE);
        return parser(bb);
    }

    public static TbaMagic preParser(TbaRpcByteBuffer buff) {
        TbaMagic magic = new TbaMagic();
        magic.setLength(buff.ReadI32());
        magic.setFlag(buff.ReadI16());
        return magic;
    }

    public static TbaRpcHead parser(TbaRpcByteBuffer buff) {
        TbaRpcHead head = new TbaRpcHead();
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

    public static void build(TbaRpcByteBuffer protcol, TbaRpcHead head, int messageLength) throws TbaException {
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

    public static int build_all(TbaRpcByteBuffer protcol, TbaRpcHead head) throws TbaException {
        int end = protcol.length();
        build(protcol, head, end);
        protcol.writeBufferBegin(end);
        return end;
    }
}
