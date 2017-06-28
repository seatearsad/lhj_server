/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.application.player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.wolfroc.slots.Util.DateTime;
import com.wolfroc.slots.Util.JsonManager;
import com.wolfroc.slots.application.player.info.PlayerFreeTimes;
import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.application.player.info.PlayerLevelBet;
import com.wolfroc.slots.application.player.info.PlayerLevelLine;
import com.wolfroc.slots.dao.PlayerDao;
import com.wolfroc.slots.exception.DaoException;
import com.wolfroc.slots.pojo.PlayerPojo;

public class PlayerManagerImpl implements PlayerManager{
	private PlayerDao playerDao;
	private Map<Long, PlayerInfo> playerInfoMap;
	
	@Override
	public PlayerInfo getPlayerInfoByUserId(int userId) throws Exception {
		PlayerPojo pojo = playerDao.getPlayerPojoByUserId(userId);
		PlayerInfo playerInfo = null;
		if (pojo != null) {
			playerInfo = setPlayerInfoFromPojo(playerInfo, pojo);
			addPlayerInfo(playerInfo);
		}
		return playerInfo;
	}
	
	@Override
	public PlayerInfo getPlayerInfo(long playerId) throws Exception {
		PlayerInfo playerInfo = null;
		if (playerInfoMap == null || playerInfoMap.get(playerId) == null) {
			PlayerPojo pojo = playerDao.getPlayerPojo(playerId);
			playerInfo = setPlayerInfoFromPojo(playerInfo,pojo);
			addPlayerInfo(playerInfo);
		}else{
			playerInfo = playerInfoMap.get(playerId);
		}
		
		return playerInfo;
	}

	@Override
	public PlayerInfo addPlayerInfo(PlayerInfo playerInfo) throws Exception {
		if (playerInfoMap == null) {
			playerInfoMap = new HashMap<Long, PlayerInfo>();
		}
		
		playerInfoMap.put(playerInfo.getId(), playerInfo);
		
		return playerInfo;
	}
	@Override
	public PlayerInfo updatePlayerInfo(PlayerInfo playerInfo) throws Exception {
		String thisTime = DateTime.getDateTimeString();
		playerInfo.setUpdateTime(thisTime);
		PlayerPojo pojo = setPlayerPojoFromPlayerInfo(playerInfo);
		
		playerDao.updatePlayer(pojo);
		
		return playerInfo;
	}
	@Override
	public PlayerInfo createPlayerInfo(int userId) throws Exception {
		PlayerPojo pojo = new PlayerPojo();
		pojo.setUserId(userId);
		//之后再修改初始名称
		String playerName = String.valueOf(userId);
		pojo.setPlayerName(playerName);
		//初始化的当前金额等于总金额
		pojo.setCurr_amount(pojo.getTotal_amount());
		
		String time = DateTime.getDateTimeString();
		
		PlayerFreeTimes freeTimes = new PlayerFreeTimes();
		List<PlayerFreeTimes> freeTimeList = new ArrayList<PlayerFreeTimes>();
		freeTimeList.add(freeTimes);
		pojo.setFree_times(JsonManager.getGson().toJson(freeTimeList));
		
		PlayerLevelBet levelBet = new PlayerLevelBet();
		List<PlayerLevelBet> levelBets = new ArrayList<PlayerLevelBet>();
		levelBets.add(levelBet);
		pojo.setLevel_bet(JsonManager.getGson().toJson(levelBets));
		
		PlayerLevelLine levelLine = new PlayerLevelLine();
		List<PlayerLevelLine> levelLines = new ArrayList<PlayerLevelLine>();
		levelLines.add(levelLine);
		pojo.setLevel_line(JsonManager.getGson().toJson(levelLines));
		
		pojo.setLoginTime(time);
		pojo.setCreateTime(time);
		pojo.setUpdateTime(time);
		
		pojo = playerDao.createPlayerPojo(pojo);
		
		PlayerInfo playerInfo = setPlayerInfoFromPojo(null, pojo);
		addPlayerInfo(playerInfo);
		
		return playerInfo;
	}
	private PlayerPojo setPlayerPojoFromPlayerInfo(PlayerInfo playerInfo) throws DaoException{
		PlayerPojo pojo = playerDao.getPlayerPojo(playerInfo.getId());
		pojo.setExp(playerInfo.getExp());
		pojo.setLevel(playerInfo.getLevel());
		pojo.setTotal_screen(playerInfo.getTotal_screen());
		pojo.setTotal_win(playerInfo.getTotal_win());
		pojo.setTotal_amount(playerInfo.getTotal_amount());
		pojo.setCurr_amount(playerInfo.getCurr_amount());
		List<PlayerFreeTimes> freeTimes = new ArrayList<PlayerFreeTimes>(playerInfo.getFree_times().values());
		pojo.setFree_times(JsonManager.getGson().toJson(freeTimes));
		List<PlayerLevelBet> levelBets = new ArrayList<PlayerLevelBet>(playerInfo.getLevel_bet().values());
		pojo.setLevel_bet(JsonManager.getGson().toJson(levelBets));
		List<PlayerLevelLine> levelLines = new ArrayList<PlayerLevelLine>(playerInfo.getLevel_line().values());
		pojo.setLevel_line(JsonManager.getGson().toJson(levelLines));
		
		pojo.setLoginTime(playerInfo.getLoginTime());
		String updateTime = DateTime.getDateTimeString();
		playerInfo.setUpdateTime(updateTime);
		pojo.setUpdateTime(updateTime);
		
		return pojo;
	}
	private PlayerInfo setPlayerInfoFromPojo(PlayerInfo info,PlayerPojo pojo){
		info = new PlayerInfo();
		
		info.setId(pojo.getId());
		info.setUserId(pojo.getUserId());
		info.setPlayerName(pojo.getPlayerName());
		info.setGender(pojo.getGender());
		info.setExp(pojo.getExp());
		info.setLevel(pojo.getLevel());
		info.setTotal_screen(pojo.getTotal_screen());
		info.setTotal_win(pojo.getTotal_win());
		info.setTotal_amount(pojo.getTotal_amount());
		info.setCurr_amount(pojo.getCurr_amount());
		
		Type type = new TypeToken<List<PlayerFreeTimes>>() {}.getType();
		List<PlayerFreeTimes> listFree = JsonManager.getGson().fromJson(pojo.getFree_times(),type);
		Map<Integer, PlayerFreeTimes> freeMap = new HashMap<Integer, PlayerFreeTimes>();
		for(PlayerFreeTimes freeTimes : listFree){
			freeMap.put(freeTimes.getLevel(), freeTimes);
		}
		info.setFree_times(freeMap);
		
		type = new TypeToken<List<PlayerLevelBet>>() {}.getType();
		List<PlayerLevelBet> list = JsonManager.getGson().fromJson(pojo.getLevel_bet(),type);
		Map<Integer, PlayerLevelBet> betMap = new HashMap<Integer, PlayerLevelBet>();
		for(PlayerLevelBet playerLevelBet : list){
			betMap.put(playerLevelBet.getLevel(), playerLevelBet);
		}
		info.setLevel_bet(betMap);
		
		type = new TypeToken<List<PlayerLevelLine>>() {}.getType();
		List<PlayerLevelLine> lineList = JsonManager.getGson().fromJson(pojo.getLevel_line(),type);
		Map<Integer, PlayerLevelLine> lineMap = new HashMap<Integer, PlayerLevelLine>();
		for(PlayerLevelLine playerLevelLine : lineList){
			lineMap.put(playerLevelLine.getLevel(), playerLevelLine);
		}
		info.setLevel_line(lineMap);
		
//		info.setLevel_bet(pojo.getLevel_bet());
//		info.setLevel_line(pojo.getLevel_line());
		info.setCreateTime(pojo.getCreateTime());
		info.setLoginTime(pojo.getLoginTime());
		info.setUpdateTime(pojo.getUpdateTime());
		info.setLoginKey();
		
		return info;
	}
	public PlayerDao getPlayerDao() {
		return playerDao;
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
}
