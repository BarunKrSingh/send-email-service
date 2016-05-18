package com.telefonica.email;
public class SendEmailAttributes {

	
	String senderEmailId;
	String recieverEmailId;
	String subject;
	String body;
	
	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}
	
	public void setRecieverEmailId(String recieverEmailId) {
		this.recieverEmailId = recieverEmailId;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	public String getSenderEmailId() {
		return senderEmailId;
	}

	public String getRecieverEmailId() {
		return recieverEmailId;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}
	
	
	
	
}
