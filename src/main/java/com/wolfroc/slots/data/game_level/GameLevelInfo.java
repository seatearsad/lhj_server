/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-6
 * @Description 
 */

package com.wolfroc.slots.data.game_level;

import java.util.List;
import java.util.Map;

import com.wolfroc.slots.data.symbol.SymbolInfo;

public class GameLevelInfo {
	private int id;
	private String symbol;
	private List<SymbolInfo> symbolInfo;
	/*
	 * 所有符号的总数量，所有卷轴
	 */
	private int symbolNum;
	/*
	 * 单列卷轴上的符号及数量
	 */
	private List<Map<Integer, ReelInfo>> reelList;
	//单列卷轴的符号单个排列
	List<List<Integer>> reelSymbolIdList;
	private String ui;
	private int line;
	private int open_level;
	private int scatterId;
	private int wildId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public List<SymbolInfo> getSymbolInfo() {
		return symbolInfo;
	}
	public void setSymbolInfo(List<SymbolInfo> symbolInfo) {
		this.symbolInfo = symbolInfo;
	}
	public int getSymbolNum() {
		return symbolNum;
	}
	public void setSymbolNum(int symbolNum) {
		this.symbolNum = symbolNum;
	}
	public List<Map<Integer, ReelInfo>> getReelList() {
		return reelList;
	}
	public void setReelList(List<Map<Integer, ReelInfo>> reelList) {
		this.reelList = reelList;
	}
	public List<List<Integer>> getReelSymbolIdList() {
		return reelSymbolIdList;
	}
	public void setReelSymbolIdList(List<List<Integer>> reelSymbolIdList) {
		this.reelSymbolIdList = reelSymbolIdList;
	}
	public String getUi() {
		return ui;
	}
	public void setUi(String ui) {
		this.ui = ui;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getOpen_level() {
		return open_level;
	}
	public void setOpen_level(int open_level) {
		this.open_level = open_level;
	}
	public int getScatterId() {
		return scatterId;
	}
	public void setScatterId(int scatterId) {
		this.scatterId = scatterId;
	}
	public int getWildId() {
		return wildId;
	}
	public void setWildId(int wildId) {
		this.wildId = wildId;
	}
}
