/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-21
 * @Description 
 */

package com.wolfroc.slots.action;

import com.wolfroc.slots.Util.DateTime;
import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.message.player.PlayerLoginReq;
import com.wolfroc.slots.message.player.PlayerLoginResp;
import com.wolfroc.slots.system.PlayerSystem;

public class PlayerLoginAction extends Action{
	private PlayerSystem playerSystem;
	@Override
	public String init(RequestMessage requestMessage,
			ResponseMessage responseMessage) throws Exception {
		PlayerLoginReq req = (PlayerLoginReq)requestMessage;
		int userId = req.getUserId();
		PlayerInfo playerInfo = playerSystem.getPlayerInfoByUserId(userId);
		
		PlayerLoginResp resp = (PlayerLoginResp)responseMessage;
		resp.setInfo(playerInfo);
		if (playerInfo == null) {
			resp.setP(false);
		}else{
			resp.setP(true);
			String today = DateTime.getDateTimeString();
			playerInfo.setLoginTime(today);
			playerInfo.setLoginKey();
			resp.setLoginKey(playerInfo.getLoginKey());
		}
		
		return resp.getData();
	}
	public PlayerSystem getPlayerSystem() {
		return playerSystem;
	}
	public void setPlayerSystem(PlayerSystem playerSystem) {
		this.playerSystem = playerSystem;
	}
}
