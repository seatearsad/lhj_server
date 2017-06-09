/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.system.impl;

import com.wolfroc.slots.application.player.PlayerManager;
import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.system.PlayerSystem;

public class PlayerSystemImpl implements PlayerSystem{
	private PlayerManager playerManager;
	@Override
	public PlayerInfo getPlayerInfoByUserId(int userId) throws Exception {
		PlayerInfo playerInfo = playerManager.getPlayerInfoByUserId(userId);
		return playerInfo;
	}
	@Override
	public PlayerInfo getPlayerInfoByPlayerId(long playerId) throws Exception {
		PlayerInfo playerInfo = playerManager.getPlayerInfo(playerId);
		return playerInfo;
	}
	@Override
	public PlayerInfo createPlayerByUserId(int userId) throws Exception {
		PlayerInfo playerInfo = playerManager.createPlayerInfo(userId);
		
		return playerInfo;
	}
	@Override
	public PlayerInfo updatePlayerInfoByGameResult(long playerId,
			int betAmount, int payAmount, boolean isWin, boolean isFree)
			throws Exception {
	    PlayerInfo playerInfo = getPlayerInfoByPlayerId(playerId);
		playerInfo.setCurr_amount(playerInfo.getCurr_amount() - betAmount + payAmount);
		playerInfo.setTotal_screen(playerInfo.getTotal_screen() + 1);
//		if (isFree) {
//			playerInfo.setFree_times(playerInfo.getFree_times() - 1);
//		}
		if (isWin) {
			playerInfo.setTotal_win(playerInfo.getTotal_win() + 1);
		}
		
		playerManager.updatePlayerInfo(playerInfo);
		return playerInfo;
	}
	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}
	
}
