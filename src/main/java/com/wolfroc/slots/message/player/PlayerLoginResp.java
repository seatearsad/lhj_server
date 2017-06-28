/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-21
 * @Description 
 */

package com.wolfroc.slots.message.player;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.ResponseMessage;


public class PlayerLoginResp extends ResponseMessage{
	private PlayerInfo info = null;
	private String loginKey;
	private boolean isP;
	public PlayerInfo getInfo() {
		return info;
	}
	public void setInfo(PlayerInfo info) {
		this.info = info;
	}
	public String getLoginKey() {
		return loginKey;
	}
	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}
	public boolean isP() {
		return isP;
	}
	public void setP(boolean isP) {
		this.isP = isP;
	}
}
