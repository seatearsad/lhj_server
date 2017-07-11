/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-11
 * @Description 
 */

package com.wolfroc.slots.object.game;

public class WinLineInfo {
	private int lineId;
	private int symbolId;
	private int num;
	private int isBonus;
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public int getSymbolId() {
		return symbolId;
	}
	public void setSymbolId(int symbolId) {
		this.symbolId = symbolId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(int isBonus) {
		this.isBonus = isBonus;
	}
}
