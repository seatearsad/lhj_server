/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.object.game;

import java.util.List;

public class GameResult {
	//输赢（是否有中奖线，不代表输赢金额） 0输 1赢
	private boolean isWin = false;
	//所赢线信息
	private List<WinLineInfo> lineList = null;
	//展现区域信息
	private List<List<Integer>> showReel = null;
	//下注线数
	private int betLine = 0;
	//下注倍数
	private int betMult = 0;
	//下注金额
	private int betAmount = 0;
	//赔付倍数
	private int payMult = 0;
	//赔付金额
	private int payAmount = 0;
	//是否是免费
	private boolean isFree = false;
	//玩家金额是否充足
	private boolean isEnough = true;
	//是否包含免费旋转奖励
	private boolean isWinFree = false;
	
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	public List<WinLineInfo> getLineList() {
		return lineList;
	}
	public void setLineList(List<WinLineInfo> lineList) {
		this.lineList = lineList;
	}
	public List<List<Integer>> getShowReel() {
		return showReel;
	}
	public void setShowReel(List<List<Integer>> showReel) {
		this.showReel = showReel;
	}
	public int getBetLine() {
		return betLine;
	}
	public void setBetLine(int betLine) {
		this.betLine = betLine;
	}
	public int getBetMult() {
		return betMult;
	}
	public void setBetMult(int betMult) {
		this.betMult = betMult;
	}
	public int getBetAmount() {
		return betAmount;
	}
	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}
	public int getPayMult() {
		return payMult;
	}
	public void setPayMult(int payMult) {
		this.payMult = payMult;
	}
	public int getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	public boolean isEnough() {
		return isEnough;
	}
	public void setEnough(boolean isEnough) {
		this.isEnough = isEnough;
	}
	public boolean isWinFree() {
		return isWinFree;
	}
	public void setWinFree(boolean isWinFree) {
		this.isWinFree = isWinFree;
	}
}
