/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-6-3
 * @Description 
 */

package com.wolfroc.slots.action;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.message.player.PlayerCreateReq;
import com.wolfroc.slots.message.player.PlayerCreateResp;
import com.wolfroc.slots.system.PlayerSystem;

public class PlayerCreateAction extends Action{
	private PlayerSystem playerSystem;
	@Override
	public String init(RequestMessage requestMessage,
			ResponseMessage responseMessage) throws Exception {
		PlayerCreateReq req = (PlayerCreateReq)requestMessage;
		int userId = req.getUserId();
		
		PlayerInfo playerInfo = playerSystem.getPlayerInfoByUserId(userId);
		if (playerInfo == null) {
			playerInfo = playerSystem.createPlayerByUserId(userId);
		}else{
			
		}
		
		PlayerCreateResp resp = (PlayerCreateResp)responseMessage;
		resp.setInfo(playerInfo);
		
		return resp.getData();
	}
	public PlayerSystem getPlayerSystem() {
		return playerSystem;
	}
	public void setPlayerSystem(PlayerSystem playerSystem) {
		this.playerSystem = playerSystem;
	}

}
