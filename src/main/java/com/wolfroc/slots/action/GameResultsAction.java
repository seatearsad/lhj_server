/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.action;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.message.game.GameGetResultReq;
import com.wolfroc.slots.message.game.GameGetResultResp;
import com.wolfroc.slots.object.game.GameResult;
import com.wolfroc.slots.system.GameSystem;
import com.wolfroc.slots.system.PlayerSystem;

public class GameResultsAction extends Action{
	private GameSystem gameSystem;
	private PlayerSystem playerSystem;
	@Override
	public String init(RequestMessage requestMessage,
			ResponseMessage responseMessage) throws Exception {
		GameGetResultReq req = (GameGetResultReq)requestMessage;
		long playerId = req.getPlayerId();
		PlayerInfo playerInfo = playerSystem.getPlayerInfoByPlayerId(playerId);
		
		GameResult gameResult = gameSystem.getGameResult(playerId);
		GameGetResultResp resp = (GameGetResultResp)responseMessage;
		resp.setGameResult(gameResult);
		resp.setPlayerInfo(playerInfo);
		
		return resp.getData();
	}
	public GameSystem getGameSystem() {
		return gameSystem;
	}
	public void setGameSystem(GameSystem gameSystem) {
		this.gameSystem = gameSystem;
	}
	public PlayerSystem getPlayerSystem() {
		return playerSystem;
	}
	public void setPlayerSystem(PlayerSystem playerSystem) {
		this.playerSystem = playerSystem;
	}
}
