/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-6-20
 * @Description 
 */

package com.wolfroc.slots.message.player;

import com.wolfroc.slots.message.RequestMessage;

public class PlayerGameLevelBetReq extends RequestMessage{
	private int levelId;
	private int bet;
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
}
