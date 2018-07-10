package com.test.TestAssignment1.Pojo;

public class SampleRequestMessage {

	private String requestMsg;
    public SampleRequestMessage()
    {
    	
    }
	public SampleRequestMessage(String reqMsg){
		this.requestMsg=reqMsg;
	}
	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}
	
	public String toString(){
		return "Request msg is " + requestMsg;
	}
}
