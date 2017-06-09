/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-22
 * @Description 
 */

package com.wolfroc.slots.application.player.info;

public class PlayerLevelBet {
	//游戏关卡id
	private int level = 0;
	//对应关卡下注倍数-单线
	private int bet = 1;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
}
