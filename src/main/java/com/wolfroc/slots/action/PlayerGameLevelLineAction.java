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
import com.wolfroc.slots.message.player.PlayerGameLevelLineReq;
import com.wolfroc.slots.message.player.PlayerGameLevelLineResp;
import com.wolfroc.slots.system.GameSystem;
import com.wolfroc.slots.system.PlayerSystem;

public class PlayerGameLevelLineAction extends Action{
	private PlayerSystem playerSystem;
	private GameSystem gameSystem;
	@Override
	public String init(RequestMessage requestMessage,
			ResponseMessage responseMessage) throws Exception {
		PlayerGameLevelLineReq req = (PlayerGameLevelLineReq)requestMessage;
		long playerId = req.getPlayerId();
		int gameLevelId = req.getLevelId();
		int line = req.getLine();
		PlayerInfo playerInfo = playerSystem.getPlayerInfoByPlayerId(playerId);
		boolean isAllow = gameSystem.checkLineIsAllow(gameLevelId,line);
		if (isAllow) {
			playerInfo = playerSystem.changeGameLevelLine(playerId,gameLevelId,line);
		}
		
		PlayerGameLevelLineResp resp = (PlayerGameLevelLineResp)responseMessage;
		resp.setInfo(playerInfo);
		resp.setAllow(isAllow);
		return resp.getData();
	}
	public PlayerSystem getPlayerSystem() {
		return playerSystem;
	}
	public void setPlayerSystem(PlayerSystem playerSystem) {
		this.playerSystem = playerSystem;
	}
	public GameSystem getGameSystem() {
		return gameSystem;
	}
	public void setGameSystem(GameSystem gameSystem) {
		this.gameSystem = gameSystem;
	}
}
