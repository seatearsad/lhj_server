/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.application.player;

import java.util.Map;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.data.game_level.GameLevelInfo;

public interface PlayerManager {
	public PlayerInfo getPlayerInfoByUserId(int userId)throws Exception;
	public PlayerInfo getPlayerInfo(long playerId) throws Exception;
	public PlayerInfo createPlayerInfo(int userId,Map<Integer, GameLevelInfo> gameLevelMap)throws Exception;
	public PlayerInfo addPlayerInfo(PlayerInfo playerInfo)throws Exception;
	public PlayerInfo updatePlayerInfo(PlayerInfo playerInfo)throws Exception;
}
