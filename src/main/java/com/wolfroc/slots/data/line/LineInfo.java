/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-6
 * @Description 
 */

package com.wolfroc.slots.data.line;

import java.util.List;

public class LineInfo {
	private int id;
	private List<Integer> include;
	private String color;
	private String desc;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getInclude() {
		return include;
	}
	public void setInclude(List<Integer> include) {
		this.include = include;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
