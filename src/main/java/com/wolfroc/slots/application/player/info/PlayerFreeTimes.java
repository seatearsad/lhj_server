/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-6-3
 * @Description 
 */

package com.wolfroc.slots.application.player.info;

public class PlayerFreeTimes {
	//游戏关卡id
	private int level = 0;
	//对应关卡的免费次数
	private int free = 0;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
}	
