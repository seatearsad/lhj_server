/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.pojo;

@SuppressWarnings("serial")
public class PlayerPojo extends BasePojo{
	private long id;
	private int userId;
	private String playerName;
	private byte gender;//0 男 1 女
	private int exp;
	private int level = 1;
	private long total_dice_times;
	private long total_dice_win;
	private long total_screen;
	private long total_win;
	private long total_amount = 10000;
	private long curr_amount;
	private String free_times;
	private String level_bet;
	private String level_line;
	private String createTime;
	private String loginTime;
	private String updateTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public byte getGender() {
		return gender;
	}
	public void setGender(byte gender) {
		this.gender = gender;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getTotal_dice_times() {
		return total_dice_times;
	}
	public void setTotal_dice_times(long total_dice_times) {
		this.total_dice_times = total_dice_times;
	}
	public long getTotal_dice_win() {
		return total_dice_win;
	}
	public void setTotal_dice_win(long total_dice_win) {
		this.total_dice_win = total_dice_win;
	}
	public long getTotal_screen() {
		return total_screen;
	}
	public void setTotal_screen(long total_screen) {
		this.total_screen = total_screen;
	}
	public long getTotal_win() {
		return total_win;
	}
	public void setTotal_win(long total_win) {
		this.total_win = total_win;
	}
	public long getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(long total_amount) {
		this.total_amount = total_amount;
	}
	public long getCurr_amount() {
		return curr_amount;
	}
	public void setCurr_amount(long curr_amount) {
		this.curr_amount = curr_amount;
	}
	public String getFree_times() {
		return free_times;
	}
	public void setFree_times(String free_times) {
		this.free_times = free_times;
	}
	public String getLevel_bet() {
		return level_bet;
	}
	public void setLevel_bet(String level_bet) {
		this.level_bet = level_bet;
	}
	public String getLevel_line() {
		return level_line;
	}
	public void setLevel_line(String level_line) {
		this.level_line = level_line;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
