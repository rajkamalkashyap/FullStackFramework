package com.fonepaisa.GenEPG.DTO;

import java.io.Serializable;

public class fetchGameDetailsDTO implements Serializable{
	private String action;
	private String data;
	private String respCode;
	private String respMsg;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "fetchGameDetailsDTO [action=" + action + ", data=" + data + ", respCode=" + respCode + ", respMsg="
				+ respMsg + "]";
	}
	
}
