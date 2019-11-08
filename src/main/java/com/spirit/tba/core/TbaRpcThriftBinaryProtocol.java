package com.spirit.tba.core;

/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
import java.nio.ByteBuffer;
import com.spirit.tba.Exception.TbaException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransport;


public class TbaRpcThriftBinaryProtocol extends TProtocol {

	private static final TStruct ANONYMOUS_STRUCT = new TStruct();
	private TbaRpcByteBuffer writeBuff = null;
	private TbaRpcByteBuffer readBuff = null;
	
	public TbaRpcThriftBinaryProtocol(){
		super(null);
	}
	
	public TbaRpcThriftBinaryProtocol(TbaRpcByteBuffer out, int offset){
		super(null);
		writeBuff = out;
		writeBuff.skipWriteBuffer(offset);
	}
	
	public TbaRpcThriftBinaryProtocol(TbaRpcByteBuffer in, int offset, int size){
		super(null);
		readBuff = new TbaRpcByteBuffer(in, offset);
	}

	protected TbaRpcThriftBinaryProtocol(TTransport trans) {
		super(trans);
	}

	@Override
	public void writeMessageBegin(TMessage message) throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeMessageEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeStructBegin(TStruct struct) throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeStructEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeFieldBegin(TField field) throws TException {
		  writeByte(field.type);
		  writeI16(field.id);
	}

	@Override
	public void writeFieldEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeFieldStop() throws TException {
		 writeByte(TType.STOP);
	}

	@Override
	public void writeMapBegin(TMap map) throws TException {
		writeByte(map.keyType);
		writeByte(map.valueType);
		writeI32(map.size);
	}

	@Override
	public void writeMapEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeListBegin(TList list) throws TException {
		 writeByte(list.elemType);
		 writeI32(list.size);
	}

	@Override
	public void writeListEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeSetBegin(TSet set) throws TException {
		  writeByte(set.elemType);
		  writeI32(set.size);
	}

	@Override
	public void writeSetEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeBool(boolean b) throws TException {

		writeBuff.writeBool(b);
	}

	@Override
	public void writeByte(byte b) throws TException {
		writeBuff.writeByte(b);
	}

	@Override
	public void writeI16(short i16) throws TException {
		try {
			writeBuff.writeI16(i16);
		} catch (TbaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeI32(int i32) throws TException {

		try {
			writeBuff.writeI32(i32);
		} catch (TbaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeI64(long i64) throws TException {
		try {
			writeBuff.writeI64(i64);
		} catch (TbaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeDouble(double dub) throws TException {
		try {
			writeBuff.WriteDouble(dub);
		} catch (TbaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeString(String str) throws TException {

		try {
			writeBuff.WriteString(str);
		} catch (TbaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeBinary(ByteBuffer buf) throws TException {
		try {
			writeBuff.WriteBinary(buf);
		} catch (TbaException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TMessage readMessageBegin() throws TException {
		 return new TMessage(new String(readBuff.toBytes()), readByte(), readI32());
	}

	@Override
	public void readMessageEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public TStruct readStructBegin() throws TException {
		return ANONYMOUS_STRUCT;
	}

	@Override
	public void readStructEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public TField readFieldBegin() throws TException {
		byte type = readByte();
		short id = type == TType.STOP ? 0 : readI16();
		return new TField("", type, id);
	}

	@Override
	public void readFieldEnd() throws TException {
		// TODO Auto-generated method stub

	}

	@Override
	public TMap readMapBegin() throws TException {
		return new TMap(readByte(), readByte(), readI32());
	}

	@Override
	public void readMapEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public TList readListBegin() throws TException {
		return new TList(readByte(), readI32());
	}

	@Override
	public void readListEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public TSet readSetBegin() throws TException {
		return new TSet(readByte(), readI32());
	}

	@Override
	public void readSetEnd() throws TException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean readBool() throws TException {
		return readBuff.ReadBool();
	}

	@Override
	public byte readByte() throws TException {
		return readBuff.ReadByte();
	}

	@Override
	public short readI16() throws TException {
		return readBuff.ReadI16();
	}

	@Override
	public int readI32() throws TException {
		return readBuff.ReadI32();
	}

	@Override
	public long readI64() throws TException {
		return readBuff.ReadI64();
	}

	@Override
	public double readDouble() throws TException {
		// TODO Auto-generated method stub
		return readBuff.ReadDouble();
	}

	@Override
	public String readString() throws TException {
		return readBuff.ReadString();
	}

	@Override
	public ByteBuffer readBinary() throws TException {
		// TODO Auto-generated method stub
		return null;
	}

}
