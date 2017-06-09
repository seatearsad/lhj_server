/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-21
 * @Description 
 */

package com.wolfroc.slots.message.player;

import com.wolfroc.slots.message.RequestMessage;

public class PlayerLoginReq extends RequestMessage{
	private int userId;
	private String verifyCode;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
