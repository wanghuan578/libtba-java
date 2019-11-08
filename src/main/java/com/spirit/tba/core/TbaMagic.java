package com.spirit.tba.core;

/**
 * @author wanghuan
 * @Date 2013/11/08 11:38
 * @licence all rights reserved
 */
public class TbaMagic {

	public static final short MAGIC_OFFSET       = 6;

	private int length;
	private short flag;

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
}
