package com.spirit.tba.core;

public class TsRpcHead {
	
	private int    	length;            
	private short  	flag;              
	private short  	type;               
	private int    	sequence;          
	private int    	source;            
	private int    	destination;       
	private int     checksum;         
	private int     attachId1;
	private int     attachId2;
	private int     attachId3;
	private int     attachId4;
	
	public TsRpcHead(){
		
		length = 0;            
		flag = 0;              
		type = 0;              
		sequence = 0;          
		source = 0;            
		destination = 0;      
		checksum = 0;
		attachId1 = 0;
		attachId2 = 0;
		attachId3 = 0;
		attachId4 = 0;
	}

	public TsRpcHead(int type){
		
		length = 0;            
		flag = 0;              
		this.type = (short)type;
		sequence = 0;          
		source = 0;            
		destination = 0;      
		checksum = 0;
		attachId1 = 0;
		attachId2 = 0;
		attachId3 = 0;
		attachId4 = 0;
	}

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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

	public int getAttachId1() {
		return attachId1;
	}

	public void setAttachId1(int attachId1) {
		this.attachId1 = attachId1;
	}

	public int getAttachId2() {
		return attachId2;
	}

	public void setAttachId2(int attachId2) {
		this.attachId2 = attachId2;
	}

	public int getAttachId3() {
		return attachId3;
	}

	public void setAttachId3(int attachId3) {
		this.attachId3 = attachId3;
	}

	public int getAttachId4() {
		return attachId4;
	}

	public void setAttachId4(int attachId4) {
		this.attachId4 = attachId4;
	}
}
