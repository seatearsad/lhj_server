/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-8-9
 * @Description 
 */

package com.wolfroc.slots.message.game;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.message.ResponseMessage;

public class GameBonusResultResp extends ResponseMessage{
	private int winNum;
	private PlayerInfo playerInfo;
	public int getWinNum() {
		return winNum;
	}
	public void setWinNum(int winNum) {
		this.winNum = winNum;
	}
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}
}
