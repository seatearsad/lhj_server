/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.system.impl;

import org.apache.log4j.Logger;

import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.object.game.GameResult;
import com.wolfroc.slots.servlet.main.AppContext;
import com.wolfroc.slots.system.GameCalculationSystem;
import com.wolfroc.slots.system.GameSystem;

public class GameSystemImpl implements GameSystem{
	private Logger logger = Logger.getLogger(getClass());
	public GameSystemImpl() {
		logger.info("GameSystemImpl");
	}
	@Override
	public GameResult getGameResult(long playerId) throws Exception {
		GameCalculationSystem gameCalculationSystem = AppContext.getInstance().getBean("gameCalculationSystem");
		GameResult result = gameCalculationSystem.getGameResult(playerId);
		return result;
	}

}
