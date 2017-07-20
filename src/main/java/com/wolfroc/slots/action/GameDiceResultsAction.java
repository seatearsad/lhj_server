/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-7-11
 * @Description 
 */

package com.wolfroc.slots.action;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.message.game.GameDiceGetResultReq;
import com.wolfroc.slots.message.game.GameDiceGetResultResp;
import com.wolfroc.slots.object.game.GameDiceResult;
import com.wolfroc.slots.system.GameSystem;
import com.wolfroc.slots.system.PlayerSystem;

public class GameDiceResultsAction extends Action{
	private GameSystem gameSystem;
	private PlayerSystem playerSystem;
	@Override
	public String init(RequestMessage requestMessage,
			ResponseMessage responseMessage) throws Exception {
		GameDiceGetResultReq req = (GameDiceGetResultReq)requestMessage;
		long playerId = req.getPlayerId();
		int los = req.getLos();
		
		GameDiceResult gameDiceResult = gameSystem.getGameDiceResult(playerId, los);
		PlayerInfo playerInfo = playerSystem.getPlayerInfoByPlayerId(playerId);
		
		GameDiceGetResultResp resp = (GameDiceGetResultResp)responseMessage;
		resp.setGameDiceResult(gameDiceResult);
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
