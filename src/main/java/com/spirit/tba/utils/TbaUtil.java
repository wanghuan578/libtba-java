package com.spirit.tba.utils;

import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TsRpcEventParser;
import com.spirit.tba.core.TsRpcMessageBuilder;
import org.apache.thrift.TBase;

public class TbaUtil<TEvent extends TBase> {

    public byte [] Serialize(TEvent entify, int buf_size) throws TbaException {
            TsRpcMessageBuilder<TEvent> builder = new TsRpcMessageBuilder<TEvent>(entify, buf_size);
            return builder.Serialize().OutStream().GetBytes();
    }

    public TEvent Deserialize(byte[] msg, Class<TEvent> clazz) throws IllegalAccessException, TbaException, InstantiationException {
        TsRpcEventParser<TEvent> parser = new TsRpcEventParser<>(msg, msg.length);
        return parser.ToEvent(clazz, 0);
    }
}
