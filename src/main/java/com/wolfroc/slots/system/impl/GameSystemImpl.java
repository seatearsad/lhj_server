/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.system.impl;

import org.apache.log4j.Logger;

import com.wolfroc.slots.data.DataManager;
import com.wolfroc.slots.data.game_level.GameLevelInfo;
import com.wolfroc.slots.object.game.GameDiceResult;
import com.wolfroc.slots.object.game.GameResult;
import com.wolfroc.slots.servlet.main.AppContext;
import com.wolfroc.slots.system.GameCalculationSystem;
import com.wolfroc.slots.system.GameDiceCalculationSystem;
import com.wolfroc.slots.system.GameSystem;

public class GameSystemImpl implements GameSystem{
	private Logger logger = Logger.getLogger(getClass());
	private DataManager dataManager;
	public GameSystemImpl() {
		logger.info("GameSystemImpl");
	}
	@Override
	public GameResult getGameResult(long playerId,int gameId) throws Exception {
		GameCalculationSystem gameCalculationSystem = AppContext.getInstance().getBean("gameCalculationSystem");
		GameResult result = gameCalculationSystem.getGameResult(playerId,gameId);
		return result;
	}
	@Override
	public GameDiceResult getGameDiceResult(long playerId, int los)
			throws Exception {
		GameDiceCalculationSystem gameDiceCalculationSystem = AppContext.getInstance().getBean("gameDiceCalculationSystem");
		GameDiceResult result = gameDiceCalculationSystem.getGameResult(playerId,los);
		return result;
	}
	@Override
	public boolean checkBetIsAllow(int gameLevel, int bet) throws Exception {
		GameLevelInfo gameLevelInfo = dataManager.getGameLevelInfo(gameLevel);
		boolean isAllow = false;
		if (gameLevelInfo.getBet().contains(bet)) {
			isAllow = true;
		}
		return isAllow;
	}
	@Override
	public boolean checkLineIsAllow(int gameLevel, int line) throws Exception {
		GameLevelInfo gameLevelInfo = dataManager.getGameLevelInfo(gameLevel);
		boolean isAllow = false;
		if(gameLevelInfo.getLine()>= line){
			isAllow = true;
		}
		return isAllow;
	}
	public DataManager getDataManager() {
		return dataManager;
	}
	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}
}
