package com.spirit.tba.core;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import com.spirit.tba.Exception.TbaException;
import org.apache.thrift.TException;
import static com.spirit.tba.Exception.ErrorType.WRITE_BUFFER_OUT_OF_RANGE_EXCEPTION;



public class TsRpcByteBuffer {
	
	private int bufferSize = 0;
	private byte [] buffer = null;
	private int writebufferEnd = 0;
	private int readbufferEnd = 0;
	//private String stringBuffer = null;
	
	public TsRpcByteBuffer(int len){
		bufferSize = len;
		writebufferEnd = 0;
		readbufferEnd = 0;
		buffer = new byte[bufferSize];
	}
	
	public TsRpcByteBuffer(byte[] data, int len){
		
		if(null != data)
		{
			writebufferEnd = len;
			readbufferEnd = 0;
			buffer = new byte[writebufferEnd];
			
			for(int index = 0; index < writebufferEnd; index++){
				buffer[index] = data[index];
			}
		}
	}

	public void writeBinary(byte [] buff, int len){
		System.arraycopy(buff, 0, buffer, writebufferEnd, len);
	}

	public TsRpcByteBuffer clone() {
		TsRpcByteBuffer clone =  new TsRpcByteBuffer(writebufferEnd);
		clone.copy(buffer, writebufferEnd);
		return clone;
	}

	private void copy(byte [] buf, int len) {
		System.arraycopy(buf, 0, buffer, 0, len);
	}

	public void append(byte [] buf) {
		System.arraycopy(buf, 0, buffer, writebufferEnd, buf.length);
		writebufferEnd += buf.length;
	}

	public TsRpcByteBuffer(TsRpcByteBuffer buff, int offset){
		bufferSize = buff.length() - offset;
		buffer = new byte[bufferSize];
		System.arraycopy(buff.toBytes(), offset, buffer, 0, bufferSize);
	}
	
	public TsRpcByteBuffer(TsRpcByteBuffer buff){
		writebufferEnd = buff.length();
		buffer = new byte[writebufferEnd];
		buffer = buff.toBytes();
	}
	
	public int length(){
		return writebufferEnd;
	}

	public byte[] toBytes(){
		byte [] dest = new byte [writebufferEnd];
		System.arraycopy(buffer, 0, dest, 0, writebufferEnd);
		return dest;
	}

	public void WriteBufferBegin(int pos){
		writebufferEnd = pos;
	}
	
	public void ReadBufferBegin(int pos){
		readbufferEnd = pos;
	}
	
	public void SkipWriteBuffer(int offset){
		
		writebufferEnd += offset;
	}
	
	public void SkipReadBuffer(int offset){
		readbufferEnd += offset;
	}
	
	public void SetBuffer(byte[] str){
		buffer = str;
	}
	
	//Write Data
	public void WriteByte(byte b){
		buffer[writebufferEnd++] = b;
	}
	
	public void WriteBool(boolean b){
		WriteByte(b ? (byte)1 : (byte)0);
	}
	
	public boolean writeI16(short i16) throws TbaException {
		
		try {
			buffer[writebufferEnd++] = (byte) (i16>>8 & 0xff);
			buffer[writebufferEnd++] = (byte) (i16 & 0xff);
		}
		catch(Exception e)
		{
			throw new TbaException(WRITE_BUFFER_OUT_OF_RANGE_EXCEPTION);
		}
		
		return true;
	}

	public boolean writeI32(int i32) throws TbaException {
		
		try
		{
			buffer[writebufferEnd++] = (byte) (i32>>24 & 0xff);
			buffer[writebufferEnd++] = (byte) (i32>>16 & 0xff);
			buffer[writebufferEnd++] = (byte) (i32>>8 & 0xff);
			buffer[writebufferEnd++] = (byte) (i32 & 0xff);
		} 
		catch (Exception e) 
		{
			throw new TbaException(WRITE_BUFFER_OUT_OF_RANGE_EXCEPTION);
		}
		
		return true;
	}
	
	public boolean writeI64(long i64) throws TbaException {
		
		try
		{
			buffer[writebufferEnd++] = (byte) (i64>>56 & 0xff);
			buffer[writebufferEnd++] = (byte) (i64>>48 & 0xff);
			buffer[writebufferEnd++] = (byte) (i64>>40 & 0xff);
			buffer[writebufferEnd++] = (byte) (i64>>32 & 0xff);
			buffer[writebufferEnd++] = (byte) (i64>>24 & 0xff);
			buffer[writebufferEnd++] = (byte) (i64>>16 & 0xff);
			buffer[writebufferEnd++] = (byte) (i64>>8 & 0xff);
			buffer[writebufferEnd++] = (byte) (i64 & 0xff);

		} 
		catch (Exception e) 
		{
			throw new TbaException(WRITE_BUFFER_OUT_OF_RANGE_EXCEPTION);
		}
		
		return true;
	}
	
	public boolean WriteDouble(double d64) throws TbaException {
		writeI64(Double.doubleToLongBits(d64));
		return true;
	}
	
	public boolean WriteString(String str) throws TbaException {
		
		byte[] dat = null;
		
		try 
		{
			dat = str.getBytes("UTF-8");
			
			if(null == dat){
				writeI32(0);
			}
			else {
				writeI32(dat.length);
				System.arraycopy(dat, 0, buffer, writebufferEnd,  dat.length);
				writebufferEnd += dat.length;
			}
		} 
		catch (UnsupportedEncodingException e) 
		{
			throw new TbaException(WRITE_BUFFER_OUT_OF_RANGE_EXCEPTION);
		}
		
	    return true;
	}
	
	public boolean WriteBinary(ByteBuffer buff) throws TbaException {
		
		int length = buff.limit() - buff.position() - buff.arrayOffset();
		writeI32(length);
		System.arraycopy(buff.array(), (buff.position() + buff.arrayOffset()), buffer, writebufferEnd, length);
		writebufferEnd += length;
		
		return true;
	}
	    
	public boolean WriteBinary(byte[] data, int length) throws TbaException {
		
		try
		{
			for(int index = 0; index < length; index++)
			{
				buffer[writebufferEnd++] = data[index];
			}
			
			writebufferEnd += length;
		}
		catch(Exception e)
		{
			throw new TbaException(WRITE_BUFFER_OUT_OF_RANGE_EXCEPTION);
		}
		
		return true;
	}
	
	public byte ReadByte(){
		return (byte)buffer[readbufferEnd++];
	}

	public boolean ReadBool(){
		
		return (boolean)(1 == ReadByte());
	}
	
	public short ReadI16(){
		
		short result = (short)(((buffer[readbufferEnd++] & 0xff) << 8) |
	       			 			(buffer[readbufferEnd++] & 0xff));
	    return result;
	}
	
	public int ReadI32(){
		
		 int result  = 	((buffer[readbufferEnd++] & 0xff) << 24) |
			      		((buffer[readbufferEnd++] & 0xff) << 16) |
			      		((buffer[readbufferEnd++] & 0xff) <<  8) |
			      		(buffer[readbufferEnd++] & 0xff);
				  
		return result;
	}
	
	public int ReadI32(int position){
		
		 int result  = 	((buffer[position + 0] & 0xff) << 24) |
			      		((buffer[position + 1] & 0xff) << 16) |
			      		((buffer[position + 2] & 0xff) <<  8) |
			      		 (buffer[position + 3] & 0xff);
				  
		return result;
	}
	
	public long ReadI64(){
		
		 long result  = ((long)(buffer[readbufferEnd++] & 0xff) << 56) |
			      		((long)(buffer[readbufferEnd++] & 0xff) << 48) |
			      		((long)(buffer[readbufferEnd++] & 0xff) << 40) |
			      		((long)(buffer[readbufferEnd++] & 0xff) << 32) |
			      		((long)(buffer[readbufferEnd++] & 0xff) << 24) |
			      		((long)(buffer[readbufferEnd++] & 0xff) << 16) |
			      		((long)(buffer[readbufferEnd++] & 0xff) <<  8) |
			      		((long)buffer[readbufferEnd++] & 0xff);
				  
		return result;
	}

	public Double ReadDouble() {
		return Double.longBitsToDouble(ReadI64());
	}

	public String ReadString() throws TException
	{
		int size = ReadI32();
		
		try 
		{			  
			byte[] buf = new byte[size];
			System.arraycopy(buffer, readbufferEnd, buf, 0, size);
			readbufferEnd += size;
			return new String(buf, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		  
		return null;
	}
	
	public void Cat(TsRpcByteBuffer in){
		System.arraycopy(in.toBytes(), 0, buffer, writebufferEnd,  in.length());
		writebufferEnd += in.length();
	}
	
}
