/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-12
 * @Description 
 */

package com.wolfroc.slots.pojo;

@SuppressWarnings("serial")
public class OverallPojo extends BasePojo{
	private int id;
	private long total_round;
	private long player_win_round;
	private long init_total_amount;
	private long curr_total_amount;
	private long curr_win_amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getTotal_round() {
		return total_round;
	}
	public void setTotal_round(long total_round) {
		this.total_round = total_round;
	}
	public long getPlayer_win_round() {
		return player_win_round;
	}
	public void setPlayer_win_round(long player_win_round) {
		this.player_win_round = player_win_round;
	}
	public long getInit_total_amount() {
		return init_total_amount;
	}
	public void setInit_total_amount(long init_total_amount) {
		this.init_total_amount = init_total_amount;
	}
	public long getCurr_total_amount() {
		return curr_total_amount;
	}
	public void setCurr_total_amount(long curr_total_amount) {
		this.curr_total_amount = curr_total_amount;
	}
	public long getCurr_win_amount() {
		return curr_win_amount;
	}
	public void setCurr_win_amount(long curr_win_amount) {
		this.curr_win_amount = curr_win_amount;
	}
}
