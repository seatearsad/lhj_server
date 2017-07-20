/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.system.impl;

import java.util.Map;

import com.wolfroc.slots.application.player.PlayerManager;
import com.wolfroc.slots.application.player.info.PlayerFreeTimes;
import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.application.player.info.PlayerLevelBet;
import com.wolfroc.slots.application.player.info.PlayerLevelLine;
import com.wolfroc.slots.data.DataManager;
import com.wolfroc.slots.data.game_level.GameLevelInfo;
import com.wolfroc.slots.object.game.GameDiceResult;
import com.wolfroc.slots.system.PlayerSystem;

public class PlayerSystemImpl implements PlayerSystem{
	private PlayerManager playerManager;
	private DataManager dataManager;
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
		Map<Integer, GameLevelInfo> gameLevelMap = dataManager.getGameLevelMap();
		PlayerInfo playerInfo = playerManager.createPlayerInfo(userId,gameLevelMap);
		
		return playerInfo;
	}
	@Override
	public PlayerInfo updateFreeTimes(int gameLevelId,int times, PlayerInfo playerInfo)
			throws Exception {
		Map<Integer, PlayerFreeTimes> freeTimeMap = playerInfo.getFree_times();
		PlayerFreeTimes freeTimes = freeTimeMap.get(gameLevelId);
		if (freeTimes == null) {
			freeTimes = new PlayerFreeTimes();
			freeTimes.setLevel(gameLevelId);
		}
		
		freeTimes.setFree(freeTimes.getFree() + times);
		freeTimes.setBet(playerInfo.getLevel_bet().get(gameLevelId).getBet());
		freeTimes.setLine(playerInfo.getLevel_line().get(gameLevelId).getLine());
		
		playerManager.updatePlayerInfo(playerInfo);
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
	@Override
	public PlayerInfo updatePlayerInfoByDiceResult(long playerId,
			GameDiceResult gameDiceResult,int bet) throws Exception {
		PlayerInfo playerInfo = getPlayerInfoByPlayerId(playerId);
		playerInfo.setCurr_amount(playerInfo.getCurr_amount() - bet);
		playerInfo.setTotal_dice_times(playerInfo.getTotal_dice_times() + 1);
		if (gameDiceResult.isWin()) {
			playerInfo.setCurr_amount(playerInfo.getCurr_amount() + bet*2);
			playerInfo.setTotal_dice_win(playerInfo.getTotal_dice_win() + 1);
		}
		playerManager.updatePlayerInfo(playerInfo);
		
		return playerInfo;
	}
	@Override
	public PlayerInfo changeGameLevelLine(long playerId, int gameLevelId, int line)
			throws Exception {
		PlayerInfo playerInfo = getPlayerInfoByPlayerId(playerId);
		Map<Integer, PlayerLevelLine> level_line = playerInfo.getLevel_line();
		PlayerLevelLine levelLine = level_line.get(gameLevelId);
		levelLine.setLine(line);
		
		level_line.put(gameLevelId, levelLine);
		
		playerManager.updatePlayerInfo(playerInfo);
		
		return playerInfo;
	}
	@Override
	public PlayerInfo changeGameLevelBet(long playerId, int gameLevelId, int bet)
			throws Exception {
		PlayerInfo playerInfo = getPlayerInfoByPlayerId(playerId);
		Map<Integer, PlayerLevelBet> betMap = playerInfo.getLevel_bet();
		PlayerLevelBet levelBet = betMap.get(gameLevelId);
		levelBet.setBet(bet);
		
		betMap.put(gameLevelId, levelBet);
		
		playerManager.updatePlayerInfo(playerInfo);
		
		return playerInfo;
	}
	public PlayerManager getPlayerManager() {
		return playerManager;
	}
	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}
	public DataManager getDataManager() {
		return dataManager;
	}
	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}
}
