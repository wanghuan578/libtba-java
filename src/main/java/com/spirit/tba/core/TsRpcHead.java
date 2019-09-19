package com.spirit.tba.core;

public class TsRpcHead {

	public static final int HEAD_SIZE = 40;
	
	private int    	length;            
	private short  	flag;              
	private short  	type;               
	private int    	sequence;          
	private int    	source;            
	private int    	destination;       
	private int     checksum;         
	private int     attach_id1;       
	private int     attach_id2;     
	private int     attach_id3;        
	private int     attach_id4;
	
	public TsRpcHead(){
		
		length = 0;            
		flag = 0;              
		type = 0;              
		sequence = 0;          
		source = 0;            
		destination = 0;      
		checksum = 0;         
		attach_id1 = 0;      
		attach_id2 = 0;   
		attach_id3 = 0;        
		attach_id4 = 0;
	}

	public TsRpcHead(int type){
		
		length = 0;            
		flag = 0;              
		this.type = (short)type;
		sequence = 0;          
		source = 0;            
		destination = 0;      
		checksum = 0;         
		attach_id1 = 0;      
		attach_id2 = 0;   
		attach_id3 = 0;        
		attach_id4 = 0;
	}

	public int GetLength(){
		return length;
	}

	public short GetFlag(){
		return flag;
	}

	public short GetType(){
		return type;
	}

	public int GetSequence(){
		return sequence;
	}

	public int GetSource(){
		return source;
	}

	public int GetDestination(){
		return destination;
	}

	public int GetCheckSum(){
		return checksum;
	}

	public int GetAttach1(){
		return attach_id1;
	}

	public int GetAttach2(){
		return attach_id2;
	}

	public int GetAttach3(){
		return attach_id3;
	}

	public int GetAttach4(){
		return attach_id4;
	}

	public void SetLength(int len){
		this.length = len;
	}

	public void SetFlag(short flag){
		this.flag = flag;
	}

	public void SetType(short type){
		this.type = type;
	}

	public void SetSequence(int seq){
		this.sequence = seq;
	}

	public void SetSource(int source){
		this.source = source;
	}

	public void SetDestination(int des){
		this.destination = des;
	}

	public void SetCheckSum(int checksum){
		this.checksum = checksum;
	}

	public void SetAttach1(int att1){
		this.attach_id1 = att1;
	}

	public void SetAttach2(int att2){
		this.attach_id2 = att2;
	}

	public void SetAttach3(int att3){
		this.attach_id3 = att3;
	}

	public void SetAttach4(int att4){
		this.attach_id4 = att4;
	}
}
