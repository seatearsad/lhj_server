/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-10-8
 * @Description 
 */

package com.wolfroc.slots.message.error;

import com.wolfroc.slots.message.ResponseMessage;

public class CommonErrorResp extends ResponseMessage{
	private String msg;
	public CommonErrorResp(){
		this.messageId = 69999;//错误协议号
	}
	public CommonErrorResp(String msg,int result){
		setMsg(msg);
		this.result = result;
		this.messageId = 69999;
		try {
			throw new Exception("Message Error messageId：" + messageId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
