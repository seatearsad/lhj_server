/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-12
 * @Description 
 */

package com.wolfroc.slots.message;

import java.io.UnsupportedEncodingException;

import com.wolfroc.slots.Util.JsonManager;



public class ResponseMessage extends Message{
	protected String data;
	protected int result = ResultCode.successCode;
	
	public ResponseMessage() {
		super();
	}
	public String getData() {
		this.objectJson();
		try {
			data=new String(data.getBytes("utf8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void objectJson(){
		this.data = JsonManager.getGson().toJson(this);
	}
}
