/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-22
 * @Description 
 */

package com.wolfroc.slots.application.player.info;

public class PlayerLevelLine {
	//游戏关卡id
	private int level = 0;
	//对应关卡线数
	private int line = 9;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
}
