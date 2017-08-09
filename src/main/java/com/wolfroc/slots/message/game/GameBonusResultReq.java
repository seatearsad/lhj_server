/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-8-9
 * @Description 
 */

package com.wolfroc.slots.message.game;

import com.wolfroc.slots.message.RequestMessage;

public class GameBonusResultReq extends RequestMessage{
	private int score;
	private int gameId;

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}
