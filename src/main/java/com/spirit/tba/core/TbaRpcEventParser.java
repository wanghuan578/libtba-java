package com.spirit.tba.core;
/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.tools.TbaHeadUtil;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import java.lang.reflect.*;
import static com.spirit.tba.Exception.ErrorType.*;

public class TbaRpcEventParser<TMessageBody extends TBase> {

	private TbaRpcByteBuffer in_stream = null;
	private TMessageBody body_;

	public TbaRpcEventParser(byte[] msg, int size){
		in_stream = new TbaRpcByteBuffer(msg, size);
	}

	public TbaRpcEventParser(TbaRpcByteBuffer msg){
		in_stream = msg;
	}

	public TbaRpcEventParser(TbaRpcByteBuffer in, int offset){
		in_stream = new TbaRpcByteBuffer(in, offset);
	}

	public TMessageBody Decode(Class<TMessageBody> clazz) throws TbaException, IllegalAccessException, InstantiationException {

		TProtocol protocol = new TbaRpcThriftBinaryProtocol(in_stream, TbaHeadUtil.HEAD_SIZE, (in_stream.length() - TbaHeadUtil.HEAD_SIZE));

		body_ = clazz.newInstance();

		try {
			body_.read(protocol);
			return body_;
		} catch (TException e) {
			throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
		}
	}

	public TMessageBody ToEvent(Class<TMessageBody> clazz, int offset) throws TbaException, IllegalAccessException, InstantiationException {

		TProtocol protocol = new TbaRpcThriftBinaryProtocol(in_stream, offset, (in_stream.length() - offset));

		body_ = clazz.newInstance();

		try {
			body_.read(protocol);
			return body_;
		} catch (TException e) {
			throw new TbaException(UNEXPECTED_EXCEPTION.SetText(e.getMessage()));
		}
	}

	public TbaRpcHead Head(){
		return TbaHeadUtil.parser(in_stream);
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
