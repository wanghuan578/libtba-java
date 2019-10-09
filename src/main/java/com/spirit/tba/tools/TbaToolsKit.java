package com.spirit.tba.tools;

import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TbaAes;
import com.spirit.tba.core.TsRpcEventParser;
import com.spirit.tba.core.TsRpcMessageBuilder;
import org.apache.thrift.TBase;
import java.io.UnsupportedEncodingException;


public class TbaToolsKit<TEvent extends TBase> {

    public byte [] serialize(TEvent ev, int buf_size) throws TbaException {
        TsRpcMessageBuilder<TEvent> builder = new TsRpcMessageBuilder<TEvent>(ev, buf_size);
        return builder.Serialize().OutStream().GetBytes();
    }

//    public String serializeAes(TEvent ev, String key, int buf_size) throws TbaException, UnsupportedEncodingException {
//        byte[] original = serialize(ev, buf_size);
//        return TbaAes.encrypt(new String(original, "ISO8859-1"), key);
//    }

    public TEvent deserialize(byte[] msg, Class<TEvent> clazz) throws IllegalAccessException, TbaException, InstantiationException {
        TsRpcEventParser<TEvent> parser = new TsRpcEventParser<>(msg, msg.length);
        return parser.ToEvent(clazz, 0);
    }

//    public TEvent deserializeAes(String msg, String key, Class<TEvent> clazz) throws IllegalAccessException, TbaException, InstantiationException, UnsupportedEncodingException {
//        String original = TbaAes.decrypt(msg, key);
//        return deserialize(original.getBytes("ISO8859-1"), clazz);
//    }
}
