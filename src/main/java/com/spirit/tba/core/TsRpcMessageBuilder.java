package com.spirit.tba.core;


import com.spirit.tba.Exception.TsException;
import org.apache.thrift.TBase;
import org.apache.thrift.protocol.TProtocol;
import static com.spirit.tba.Exception.ErrorType.*;


public class TsRpcMessageBuilder<TMessageBody extends TBase>{

	private TMessageBody body_;
	private TsRpcHead head_;
	private TsRpcByteBuffer out_stream = null;
	private TProtocol protocol = null;

	public TsRpcMessageBuilder(final TMessageBody concrate, final TsRpcHead head, int buff_size, int offset){

		out_stream = new TsRpcByteBuffer(buff_size);
		protocol = new TsRpcThriftBinaryProtocol(out_stream, offset);
		head_ = head;
		body_ = concrate;
	}

	public TsRpcMessageBuilder(final TMessageBody concrate, int buff_size){

		out_stream = new TsRpcByteBuffer(buff_size);
		protocol = new TsRpcThriftBinaryProtocol(out_stream, 0);
		body_ = concrate;
	}

	public int Encode() throws TsException {
		try {
			body_.write(protocol);
			return SerializeHead(head_);
		}
		catch (Exception e) {
			throw new TsException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
		}
	}

	public TsRpcMessageBuilder Serialize() throws TsException {
		try {
			body_.write(protocol);
			return this;
		}
		catch (Exception e) {
			throw new TsException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
		}
	}

	public int SerializeHead(TsRpcHead head) throws TsException {

		int end = out_stream.Length();

		out_stream.WriteBufferBegin(0);
		out_stream.WriteI32(end);
		out_stream.WriteI16(head.GetFlag());
		out_stream.WriteI16(head.GetType());
		out_stream.WriteI32(head.GetSequence());
		out_stream.WriteI32(head.GetSource());
		out_stream.WriteI32(head.GetDestination());
		out_stream.WriteI32(head.GetCheckSum());
		out_stream.WriteI32(head.GetAttach1());
		out_stream.WriteI32(head.GetAttach2());
		out_stream.WriteI32(head.GetAttach3());
		out_stream.WriteI32(head.GetAttach4());
		out_stream.WriteBufferBegin(end);

		return end;
	}

	public TsRpcByteBuffer OutStream() {
		return out_stream;
	}


}
