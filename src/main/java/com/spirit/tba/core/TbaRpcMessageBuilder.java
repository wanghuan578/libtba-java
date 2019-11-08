package com.spirit.tba.core;

/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.exception.TbaException;
import com.spirit.tba.tools.TbaHeadUtil;
import org.apache.thrift.TBase;
import org.apache.thrift.protocol.TProtocol;
import static com.spirit.tba.exception.ErrorType.*;


public class TbaRpcMessageBuilder<TMessageBody extends TBase>{

	private TMessageBody body_;
	private TbaRpcHead head_;
	private TbaRpcByteBuffer out_stream = null;
	private TProtocol protocol = null;

	public TbaRpcMessageBuilder(final TMessageBody concrate, final TbaRpcHead head, int buff_size, int offset){

		out_stream = new TbaRpcByteBuffer(buff_size);
		protocol = new TbaRpcThriftBinaryProtocol(out_stream, offset);
		head_ = head;
		body_ = concrate;
	}

	public TbaRpcMessageBuilder(final TMessageBody concrate, int buff_size){

		out_stream = new TbaRpcByteBuffer(buff_size);
		protocol = new TbaRpcThriftBinaryProtocol(out_stream, 0);
		body_ = concrate;
	}

	public int Encode() throws TbaException {
		try {
			body_.write(protocol);
			return TbaHeadUtil.build_all(out_stream, head_);
			//return SerializeHead(head_);
		}
		catch (Exception e) {
			throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
		}
	}

	public TbaRpcMessageBuilder Serialize() throws TbaException {
		try {
			body_.write(protocol);
			return this;
		}
		catch (Exception e) {
			throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
		}
	}

	public TbaRpcByteBuffer OutStream() {
		return out_stream;
	}


}
