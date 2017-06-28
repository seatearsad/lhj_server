/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-6-20
 * @Description 
 */

package com.wolfroc.slots.action;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.message.player.PlayerGameLevelBetReq;
import com.wolfroc.slots.message.player.PlayerGameLevelBetResp;
import com.wolfroc.slots.system.PlayerSystem;

public class PlayerGameLevelBetAction extends Action{
	private PlayerSystem playerSystem;
	@Override
	public String init(RequestMessage requestMessage,
			ResponseMessage responseMessage) throws Exception {
		PlayerGameLevelBetReq req = (PlayerGameLevelBetReq)requestMessage;
		long playerId = req.getPlayerId();
		int gameLevelId = req.getLevelId();
		int bet = req.getBet();
		
		PlayerInfo playerInfo = playerSystem.changeGameLevelBet(playerId,gameLevelId,bet);
		
		PlayerGameLevelBetResp resp = (PlayerGameLevelBetResp)responseMessage;
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
