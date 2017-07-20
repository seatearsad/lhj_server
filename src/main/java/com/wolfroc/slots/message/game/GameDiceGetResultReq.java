/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-7-11
 * @Description 
 */

package com.wolfroc.slots.message.game;

import com.wolfroc.slots.message.RequestMessage;

public class GameDiceGetResultReq extends RequestMessage{
	//押注大或者小 0为小 1为大
	private int los;

	public int getLos() {
		return los;
	}

	public void setLos(int los) {
		this.los = los;
	}
}
