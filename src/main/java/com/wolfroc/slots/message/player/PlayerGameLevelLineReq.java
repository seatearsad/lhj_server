/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-6-20
 * @Description 
 */

package com.wolfroc.slots.message.player;

import com.wolfroc.slots.message.RequestMessage;

public class PlayerGameLevelLineReq extends RequestMessage{
	private int levelId;
	private int line;
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
}
