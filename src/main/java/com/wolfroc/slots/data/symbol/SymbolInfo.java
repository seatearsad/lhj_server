/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-6
 * @Description 
 */

package com.wolfroc.slots.data.symbol;

import java.util.List;

public class SymbolInfo {
	private int id;
	/*
	 * 0 普通符号
	 * 1 野蛮符号wild
	 * 2 分数符号scatter
	 * 3 特别符号bouns
	 */
	private int type;
	private String name;
	private String icon;
	private List<Integer> weight;
	/*
	 * 0 不可重复
	 * 1 可重复
	 */
	private int repeat;
	private List<SymbolReward> reward;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<Integer> getWeight() {
		return weight;
	}
	public void setWeight(List<Integer> weight) {
		this.weight = weight;
	}
	public int getRepeat() {
		return repeat;
	}
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	public List<SymbolReward> getReward() {
		return reward;
	}
	public void setReward(List<SymbolReward> reward) {
		this.reward = reward;
	}
}
