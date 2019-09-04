package com.spirit.tba.core;

import com.spirit.tba.Exception.TbaException;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import java.lang.reflect.*;
import static com.spirit.tba.Exception.ErrorType.*;

public class TsRpcEventParser<TMessageBody extends TBase> {

	private TsRpcByteBuffer in_stream = null;
	private TMessageBody body_;

	public TsRpcEventParser(byte[] msg, int size){
		in_stream = new TsRpcByteBuffer(msg, size);
	}

	public TsRpcEventParser(TsRpcByteBuffer msg){
		in_stream = msg;
	}

	public TsRpcEventParser(TsRpcByteBuffer in, int offset){
		in_stream = new TsRpcByteBuffer(in, offset);
	}

	public TMessageBody Decode(Class<TMessageBody> clazz) throws TbaException, IllegalAccessException, InstantiationException {

		TProtocol protocol = new TsRpcThriftBinaryProtocol(in_stream, TsRpcHead.HEAD_SIZE, (in_stream.Length() - TsRpcHead.HEAD_SIZE));

		body_ = clazz.newInstance();

		try {
			body_.read(protocol);
			return body_;
		} catch (TException e) {
			throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
		}
	}

	public TMessageBody ToEvent(Class<TMessageBody> clazz, int offset) throws TbaException, IllegalAccessException, InstantiationException {

		TProtocol protocol = new TsRpcThriftBinaryProtocol(in_stream, offset, (in_stream.Length() - offset));

		body_ = clazz.newInstance();

		try {
			body_.read(protocol);
			return body_;
		} catch (TException e) {
			throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
		}
	}

	public TsRpcHead Head(){

		TsRpcHead head = new TsRpcHead();

		head.SetLength(in_stream.ReadI32());
		head.SetFlag(in_stream.ReadI16());
		head.SetType(in_stream.ReadI16());
		head.SetSequence(in_stream.ReadI32());
		head.SetSource(in_stream.ReadI32());
		head.SetDestination(in_stream.ReadI32());
		head.SetCheckSum(in_stream.ReadI32());
		head.SetAttach1(in_stream.ReadI32());
		head.SetAttach2(in_stream.ReadI32());
		head.SetAttach3(in_stream.ReadI32());
		head.SetAttach4(in_stream.ReadI32());

		return head;
	}

	private TMessageBody createT() {

		try {
			Type superClass = getClass().getGenericSuperclass();
			Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
			Class<?> clazz = getRawType(type);
			return (TMessageBody) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Class<?> getRawType(Type type) {
		if (type instanceof Class) {
			return (Class) type;
		} else if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type rawType = parameterizedType.getRawType();
			return (Class) rawType;
		} else if (type instanceof GenericArrayType) {
			Type componentType = ((GenericArrayType) type).getGenericComponentType();
			return Array.newInstance(getRawType(componentType), 0).getClass();
		} else if (type instanceof TypeVariable) {
			return Object.class;
		} else if (type instanceof WildcardType) {
			return getRawType(((WildcardType) type).getUpperBounds()[0]);
		} else {
			String className = type == null ? "null" : type.getClass().getName();
			throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
		}
	}
}
