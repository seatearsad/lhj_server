/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-7-11
 * @Description 
 */

package com.wolfroc.slots.system;

import com.wolfroc.slots.object.game.GameDiceResult;

public interface GameDiceCalculationSystem {
	public GameDiceResult getGameResult(long playerId,int los) throws Exception;
}
