package com.spirit.tba.core;

import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.tools.TbaHeadUtil;
import org.apache.thrift.TBase;


public class TsRpcProtocolFactory <TMessageBody extends TBase> {

	private TsRpcEventParser event_parser;
	private TsRpcMessageBuilder message_builder;

	public TsRpcProtocolFactory (final TMessageBody body, final TsRpcHead head, int buff_size) {
		message_builder = new TsRpcMessageBuilder(body, head, buff_size, TbaHeadUtil.HEAD_SIZE);
	}

	public TsRpcProtocolFactory(final TsRpcByteBuffer buff){
		event_parser = new TsRpcEventParser(buff);
	}

	public TsRpcProtocolFactory Encode() throws TbaException {
		message_builder.Encode();
		return this;
	}

	public TMessageBody Decode(Class<TMessageBody> clazz) throws TbaException, InstantiationException, IllegalAccessException {
		return (TMessageBody) event_parser.Decode(clazz);
	}

	public TsRpcByteBuffer OutStream() {
		return message_builder.OutStream();
	}
}
