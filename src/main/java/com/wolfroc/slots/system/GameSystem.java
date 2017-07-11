/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.system;

import com.wolfroc.slots.object.game.GameResult;

public interface GameSystem {
	public GameResult getGameResult(long playerId)throws Exception;
	//验证修改的bet是否被允许
	public boolean checkBetIsAllow(int gameLevel,int bet)throws Exception;
	//验证修改的line是否被允许
	public boolean checkLineIsAllow(int gameLevel,int line)throws Exception;
}
