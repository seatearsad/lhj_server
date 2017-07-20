/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-7-11
 * @Description 
 */

package com.wolfroc.slots.message.game;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.object.game.GameDiceResult;

public class GameDiceGetResultResp extends ResponseMessage{
	private GameDiceResult gameDiceResult;
	private PlayerInfo playerInfo;
	public GameDiceResult getGameDiceResult() {
		return gameDiceResult;
	}
	public void setGameDiceResult(GameDiceResult gameDiceResult) {
		this.gameDiceResult = gameDiceResult;
	}
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}
}
