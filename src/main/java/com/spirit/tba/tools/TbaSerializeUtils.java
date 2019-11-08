package com.spirit.tba.tools;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.exception.TbaException;
import com.spirit.tba.core.TbaRpcEventParser;
import com.spirit.tba.core.TbaRpcMessageBuilder;
import org.apache.thrift.TBase;


public class TbaSerializeUtils<TEvent extends TBase> {

    public byte [] serialize(TEvent ev, int buf_size) throws TbaException {
        TbaRpcMessageBuilder<TEvent> builder = new TbaRpcMessageBuilder<TEvent>(ev, buf_size);
        return builder.Serialize().OutStream().toBytes();
    }

    public TEvent deserialize(byte[] msg, Class<TEvent> clazz) throws IllegalAccessException, TbaException, InstantiationException {
        TbaRpcEventParser<TEvent> parser = new TbaRpcEventParser<>(msg, msg.length);
        return parser.ToEvent(clazz, 0);
    }

    public static int [] long2int(Long in) {
        int [] out = new int[2];
        out[0] = (int) (in & 0x000000ffffffffL);
        out[1] = (int) (in >> 32);
        return out;
    }

    public static long int2long(int [] in) {
        return in[0] | in[1] << 32;
    }

}
