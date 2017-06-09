/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.system;

import com.wolfroc.slots.application.player.info.PlayerInfo;

public interface PlayerSystem {
	public PlayerInfo createPlayerByUserId(int userId)throws Exception;
	public PlayerInfo getPlayerInfoByPlayerId(long playerId)throws Exception;
	public PlayerInfo getPlayerInfoByUserId(int userId)throws Exception;
	public PlayerInfo updatePlayerInfoByGameResult(long playerId,int betAmount,int payAmount,boolean isWin,boolean isFree)throws Exception;
}
