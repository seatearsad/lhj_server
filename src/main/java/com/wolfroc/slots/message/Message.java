/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-12
 * @Description 
 */

package com.wolfroc.slots.message;

public abstract class Message {
	protected int messageId;
	protected int serverId = 0;
	protected int playerId = 0;
	
	public Message()
	{
		
	}
	
	public int getMessageId() {
		return messageId;
	}
	

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getPlayerId() {
		return playerId;
	}


	public int getServerId() {
		return serverId;
	}
}
