/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-8-9
 * @Description 
 */

package com.wolfroc.slots.action;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.message.game.GameBonusResultReq;
import com.wolfroc.slots.message.game.GameBonusResultResp;
import com.wolfroc.slots.system.PlayerSystem;

public class GameBonusResultsAction extends Action{
	private PlayerSystem playerSystem;
	@Override
	public String init(RequestMessage requestMessage,
			ResponseMessage responseMessage) throws Exception {
		GameBonusResultReq req = (GameBonusResultReq)requestMessage;
		int score = req.getScore();
		int gameId = req.getGameId();
		long playerId = req.getPlayerId();
		PlayerInfo playerInfo = playerSystem.updateBonusScore(playerId,gameId,score);
		int winNum = score * playerInfo.getLevel_bet().get(gameId).getBet();
		
		GameBonusResultResp resp = (GameBonusResultResp)responseMessage;
		resp.setPlayerInfo(playerInfo);
		resp.setWinNum(winNum);
		
		return resp.getData();
	}
	public PlayerSystem getPlayerSystem() {
		return playerSystem;
	}
	public void setPlayerSystem(PlayerSystem playerSystem) {
		this.playerSystem = playerSystem;
	}
}
