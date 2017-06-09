/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.system;

import com.wolfroc.slots.object.game.GameResult;

public interface GameCalculationSystem {
	public GameResult getGameResult(long playerId) throws Exception;
}
