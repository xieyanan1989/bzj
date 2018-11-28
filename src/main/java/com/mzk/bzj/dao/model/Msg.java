package com.mzk.bzj.dao.model;

import java.util.Arrays;

public class Msg {

	private String message;
	private String status;
	private String encrypt;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	@Override
	public String toString() {
		return "{message=" + message + ", status=" + status + ", encrypt=" + encrypt + "}";
	}
	
	
	
	
}
