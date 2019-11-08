package com.spirit.tba.core;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.exception.TbaException;
import com.spirit.tba.tools.TbaHeadUtil;
import org.apache.thrift.TBase;


public class TbaRpcProtocolFactory <TMessageBody extends TBase> {

	private TbaRpcEventParser event_parser;
	private TbaRpcMessageBuilder message_builder;

	public TbaRpcProtocolFactory (final TMessageBody body, final TbaRpcHead head, int buff_size) {
		message_builder = new TbaRpcMessageBuilder(body, head, buff_size, TbaHeadUtil.HEAD_SIZE);
	}

	public TbaRpcProtocolFactory(final TbaRpcByteBuffer buff){
		event_parser = new TbaRpcEventParser(buff);
	}

	public TbaRpcProtocolFactory Encode() throws TbaException {
		message_builder.Encode();
		return this;
	}

	public TMessageBody Decode(Class<TMessageBody> clazz) throws TbaException, InstantiationException, IllegalAccessException {
		return (TMessageBody) event_parser.Decode(clazz);
	}

	public TbaRpcByteBuffer OutStream() {
		return message_builder.OutStream();
	}
}
