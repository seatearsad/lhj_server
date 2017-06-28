/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-6-20
 * @Description 
 */

package com.wolfroc.slots.message.player;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.ResponseMessage;

public class PlayerGameLevelBetResp extends ResponseMessage{
	private PlayerInfo info;

	public PlayerInfo getInfo() {
		return info;
	}

	public void setInfo(PlayerInfo info) {
		this.info = info;
	}

}
