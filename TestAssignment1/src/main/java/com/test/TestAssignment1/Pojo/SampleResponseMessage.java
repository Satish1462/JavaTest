package com.test.TestAssignment1.Pojo;

public class SampleResponseMessage {

	private String respMsg;
	public SampleResponseMessage(){
		
	}
	public SampleResponseMessage(String respMsg){
		
		this.respMsg=respMsg;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	public String toString(){
		
		return "response msg is" + respMsg;
	}
}
