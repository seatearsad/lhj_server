/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-12
 * @Description 
 */

package com.wolfroc.slots.message;

import com.wolfroc.slots.Util.JsonManager;
import com.wolfroc.slots.application.player.info.PlayerInfo;


public abstract class RequestMessage extends Message{
	protected transient String data;
	protected String loginKey;
	protected PlayerInfo playerInfo;
	
	public RequestMessage() {
		super();
	}
	
	public void setData(int pId,int sId,String data)
	{
		super.playerId=pId;
		super.serverId=sId;
		this.data=data;
	}
	public final void jsonToObject()
	{
		JsonManager.getGson().fromJson(data, this.getClass());
	}

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}
}
