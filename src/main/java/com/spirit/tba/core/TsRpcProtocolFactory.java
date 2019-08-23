package com.spirit.tba.core;

import com.spirit.tba.Exception.TsException;
import org.apache.thrift.TBase;


public class TsRpcProtocolFactory <TMessageBody extends TBase> {

	private TsRpcEventParser event_parser;
	private TsRpcMessageBuilder message_builder;

	public TsRpcProtocolFactory (final TMessageBody body, final TsRpcHead head, int buff_size) {
		message_builder = new TsRpcMessageBuilder(body, head, buff_size, TsRpcHead.Size());
	}

	public TsRpcProtocolFactory(final TsRpcByteBuffer buff){
		event_parser = new TsRpcEventParser(buff);
	}

	public TsRpcProtocolFactory Encode() throws TsException {
		message_builder.Encode();
		return this;
	}

	public TMessageBody Decode(Class<TMessageBody> clazz) throws TsException, InstantiationException, IllegalAccessException {
		return (TMessageBody) event_parser.Decode(clazz);
	}

	public TsRpcByteBuffer OutStream() {
		return message_builder.OutStream();
	}
}
