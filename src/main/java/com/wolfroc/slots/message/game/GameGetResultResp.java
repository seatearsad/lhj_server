/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.message.game;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.object.game.GameResult;

public class GameGetResultResp extends ResponseMessage{
	private GameResult GameResult;
	private PlayerInfo playerInfo;

	public GameResult getGameResult() {
		return GameResult;
	}

	public void setGameResult(GameResult gameResult) {
		GameResult = gameResult;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}
}
