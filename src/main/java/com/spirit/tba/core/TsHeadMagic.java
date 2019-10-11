package com.spirit.tba.core;

public class TsHeadMagic {

	public static final short MAGIC_OFFSET       = 6;

	private int    	length;
	private short  	flag;
	private short  	type;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public short getFlag() {
		return flag;
	}

	public void setFlag(short flag) {
		this.flag = flag;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}
}
