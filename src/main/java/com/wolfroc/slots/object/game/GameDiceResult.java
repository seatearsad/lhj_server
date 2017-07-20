/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-7-11
 * @Description 
 */

package com.wolfroc.slots.object.game;

import java.util.List;

public class GameDiceResult {
	private boolean isWin;
	//玩家金额是否充足
	private boolean isEnough;
	private int los;//0 smail 1 big
	//总点数
	private int total;
	private List<Integer> diceOne;
	private int re_los;//0 smail 1 big
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	public boolean isEnough() {
		return isEnough;
	}
	public void setEnough(boolean isEnough) {
		this.isEnough = isEnough;
	}
	public int getLos() {
		return los;
	}
	public void setLos(int los) {
		this.los = los;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Integer> getDiceOne() {
		return diceOne;
	}
	public void setDiceOne(List<Integer> diceOne) {
		this.diceOne = diceOne;
	}
	public int getRe_los() {
		return re_los;
	}
	public void setRe_los(int re_los) {
		this.re_los = re_los;
	}
}
