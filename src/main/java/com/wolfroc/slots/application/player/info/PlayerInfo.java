/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.application.player.info;

import java.util.Map;

import com.wolfroc.slots.Util.MD5;


public class PlayerInfo {
	private long id;
	private transient int userId;
	private String playerName;
	private byte gender;//0 男 1 女
	private int exp;
	private int level;
	private transient long total_dice_times;
	private transient long total_dice_win;
	private transient long total_screen;
	private transient long total_win;
	private transient long total_amount;
	private long curr_amount;
	private Map<Integer, PlayerFreeTimes> free_times;
	private Map<Integer, PlayerLevelBet> level_bet;
	private Map<Integer, PlayerLevelLine> level_line;
	private transient String createTime;
	private transient String loginTime;
	private transient String updateTime;
	private transient String loginKey;
	//记录此次最高的Bonus倍数
	private transient int lastBonus;
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
	public Map<Integer, PlayerFreeTimes> getFree_times() {
		return free_times;
	}
	public void setFree_times(Map<Integer, PlayerFreeTimes> free_times) {
		this.free_times = free_times;
	}
	public Map<Integer, PlayerLevelBet> getLevel_bet() {
		return level_bet;
	}
	public void setLevel_bet(Map<Integer, PlayerLevelBet> level_bet) {
		this.level_bet = level_bet;
	}
	public Map<Integer, PlayerLevelLine> getLevel_line() {
		return level_line;
	}
	public void setLevel_line(Map<Integer, PlayerLevelLine> level_line) {
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
	public int getLastBonus() {
		return lastBonus;
	}
	public void setLastBonus(int lastBonus) {
		this.lastBonus = lastBonus;
	}
	public String getLoginKey() {
		return loginKey;
	}
	public void setLoginKey() {
		String str = String.valueOf(id) + playerName + loginTime;
		String loginKey = MD5.MD5Encode(str);
		this.loginKey = loginKey;
	}
}
