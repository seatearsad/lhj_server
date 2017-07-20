/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.wolfroc.slots.application.overall.OverallManager;
import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.data.DataManager;
import com.wolfroc.slots.object.game.GameDiceResult;
import com.wolfroc.slots.system.GameDiceCalculationSystem;
import com.wolfroc.slots.system.PlayerSystem;

public class GameDiceCalculationSystemImpl implements GameDiceCalculationSystem{
	private Logger logger = Logger.getLogger(getClass());
	private DataManager dataManager;
	private OverallManager overallManager;
	private PlayerSystem playerSystem;
	
	private int diceAllNum = 6;
	private int diceNum = 3;
	private List<Integer> diceOneNum;
	public GameDiceCalculationSystemImpl() {
		logger.info("New GameDiceCalculationSystemImpl!");
	}

	@Override
	public GameDiceResult getGameResult(long playerId,int los) throws Exception {
		GameDiceResult gameDiceResult = new GameDiceResult();
		int bet = dataManager.getDiceInfo().getBet();
		PlayerInfo playerInfo = playerSystem.getPlayerInfoByPlayerId(playerId);
		if (playerInfo.getCurr_amount() < bet) {
			gameDiceResult.setEnough(false);
		}else{
			gameDiceResult.setEnough(true);
		}
		int totalPoints = getDiceTotalPoints();
		boolean isWin = false;
		int reLos = 0;
		
		if(totalPoints > dataManager.getDiceInfo().getLarge()){
			if (los == 1) {
				isWin = true;
			}
			reLos = 1;
		}else{
			if (los == 0) {
				isWin = true;
			}
		}
		gameDiceResult.setWin(isWin);
		gameDiceResult.setDiceOne(diceOneNum);
		gameDiceResult.setLos(los);
		gameDiceResult.setTotal(totalPoints);
		gameDiceResult.setRe_los(reLos);
		
		//更新个人信息
		playerInfo = playerSystem.updatePlayerInfoByDiceResult(playerId, gameDiceResult,bet);
		//更新全局统计
		overallManager.updateDiceOverall(bet, isWin);
		
		return gameDiceResult;
	}
	private int getDiceTotalPoints(){
		int total = 0;
		diceOneNum = new ArrayList<Integer>();
		for(int i=0;i<diceNum;++i){
			int thisNum = getRandomNum(diceAllNum) + 1;
			diceOneNum.add(thisNum);
			total += thisNum;
		}
		return total;
	}
	private int getRandomNum(int num){
		Random random = new Random();
		int thisNum = random.nextInt(num);
		
		return thisNum;
	}
	public DataManager getDataManager() {
		return dataManager;
	}
	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	public OverallManager getOverallManager() {
		return overallManager;
	}
	public void setOverallManager(OverallManager overallManager) {
		this.overallManager = overallManager;
	}
	public PlayerSystem getPlayerSystem() {
		return playerSystem;
	}
	public void setPlayerSystem(PlayerSystem playerSystem) {
		this.playerSystem = playerSystem;
	}
}
