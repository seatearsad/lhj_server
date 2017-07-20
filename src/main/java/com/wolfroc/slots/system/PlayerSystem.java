/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.system;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.object.game.GameDiceResult;

public interface PlayerSystem {
	public PlayerInfo createPlayerByUserId(int userId)throws Exception;
	public PlayerInfo getPlayerInfoByPlayerId(long playerId)throws Exception;
	public PlayerInfo getPlayerInfoByUserId(int userId)throws Exception;
	public PlayerInfo updateFreeTimes(int gameLevelId,int times,PlayerInfo playerInfo)throws Exception;
	public PlayerInfo updatePlayerInfoByGameResult(long playerId,int betAmount,int payAmount,boolean isWin,boolean isFree)throws Exception;
	public PlayerInfo changeGameLevelLine(long playerId,int gameLevelId,int line)throws Exception;
	public PlayerInfo changeGameLevelBet(long playerId,int gameLevelId,int bet)throws Exception;
	public PlayerInfo updatePlayerInfoByDiceResult(long playerId,GameDiceResult gameDiceResult,int bet)throws Exception;
}
