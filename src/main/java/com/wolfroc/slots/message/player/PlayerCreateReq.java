/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-6-3
 * @Description 
 */

package com.wolfroc.slots.message.player;

import com.wolfroc.slots.message.RequestMessage;

public class PlayerCreateReq extends RequestMessage{
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
