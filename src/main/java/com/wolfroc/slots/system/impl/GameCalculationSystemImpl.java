/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-10
 * @Description 
 */

package com.wolfroc.slots.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.wolfroc.slots.application.overall.OverallManager;
import com.wolfroc.slots.application.player.info.PlayerInfo;
import com.wolfroc.slots.data.DataManager;
import com.wolfroc.slots.data.game_level.GameLevelInfo;
import com.wolfroc.slots.data.game_level.ReelInfo;
import com.wolfroc.slots.data.line.LineInfo;
import com.wolfroc.slots.data.symbol.SymbolInfo;
import com.wolfroc.slots.data.symbol.SymbolReward;
import com.wolfroc.slots.object.game.GameResult;
import com.wolfroc.slots.object.game.WinLineInfo;
import com.wolfroc.slots.pojo.OverallPojo;
import com.wolfroc.slots.system.GameCalculationSystem;
import com.wolfroc.slots.system.PlayerSystem;

public class GameCalculationSystemImpl implements GameCalculationSystem{
	private Logger logger = Logger.getLogger(getClass());
	private DataManager dataManager;
	private OverallManager overallManager;
	private PlayerSystem playerSystem;
	
	//下注倍数
	private int betMult = 1;
	//赔付倍数
	private int payMult = 0;
	
	//下注线数
	private int betLine = 0;
	
	private PlayerInfo playerInfo;
	
	//关卡ID之后传输过来
	private int gameLevelId = 0;
	
	public GameCalculationSystemImpl() {
		logger.info("New GameCalculationSystemImpl!");
	}
	@Override
	public GameResult getGameResult(long playerId,int gameId) throws Exception {
		GameResult result = new GameResult();
		
		gameLevelId = gameId;
		
		playerInfo = playerSystem.getPlayerInfoByPlayerId(playerId);
		//取当前关卡的线数及下注数
		betMult = playerInfo.getLevel_bet().get(gameLevelId).getBet();
		betLine = playerInfo.getLevel_line().get(gameLevelId).getLine();
		
		GameLevelInfo gameLevelInfo = dataManager.getGameLevelInfo(gameLevelId);
		
		//获取用户信息
		PlayerInfo playerInfo = playerSystem.getPlayerInfoByPlayerId(playerId);
		
		//下注金额
		int betAmount = betMult * betLine;
		
		//是否有免费次数
		if (playerInfo.getFree_times().get(gameLevelId).getFree() > 0) {
			//修改获得免费次数时的线数以及下注数
			if(betMult != playerInfo.getFree_times().get(gameLevelId).getBet() || betLine != playerInfo.getFree_times().get(gameLevelId).getLine()){
				betMult = playerInfo.getFree_times().get(gameLevelId).getBet();
				betLine = playerInfo.getFree_times().get(gameLevelId).getLine();
				//更新角色的存储
				playerSystem.changeGameLevelBet(playerId, gameLevelId, betMult);
				playerSystem.changeGameLevelLine(playerId, gameLevelId, betLine);
			}
			result.setFree(true);
			betAmount = 0;
			//更新免费次数
			playerSystem.updateFreeTimes(gameLevelId, -1, playerInfo);
		}
		
		//判断用户是否有足够的金额进行游戏
		if (playerInfo.getCurr_amount() >= betAmount) {
			//获取使用卷轴-通过当前的下注，以及全局和用户状态
			List<List<Integer>> reelList = initReelSort(gameLevelInfo,playerInfo,betAmount);
			//取选中符号 列数->位置
			List<List<Integer>> showReel = getSelectSymbol(reelList);
			List<WinLineInfo> lineList = checkLine(showReel,gameLevelInfo);
			
			if (lineList.size() > 0) {
				result.setWin(true);
			}
			//免费旋转中奖
			for(WinLineInfo info : lineList){
				if (info.getLineId() == -1) {
					result.setWinFree(true);
				}
			}
			
			result.setLineList(lineList);
			result.setShowReel(showReel);
			//赔付金额
			int payAmount = payMult * betMult;
			result.setPayMult(payMult);
			result.setBetMult(betMult);
			result.setBetAmount(betAmount);
			result.setPayMult(payMult);
			result.setPayAmount(payAmount);
			
			//更新个人信息
			playerSystem.updatePlayerInfoByGameResult(playerId,betAmount,payAmount,result.isWin(),result.isFree());
			//更新全局统计
			overallManager.updateOverall(betAmount,payAmount,result.isWin());
		}else{
			result.setEnough(false);
		}
		
		return result;
	}
	//初始化卷轴 符号的排序
	private List<List<Integer>> initReelSort(GameLevelInfo gameLevelInfo,PlayerInfo playerInfo,int betAmount) throws Exception{
		//概率编号
		int serialNum = 0;
		//获取全局信息
		OverallPojo overall = overallManager.getOverallPojo();
		//获取比例map
		Map<Integer, List<Integer>> ratioMap = dataManager.getRatioMap();
		
		if(overall.getCurr_win_amount() >= 0 && betAmount > 0){//当全局属于盈利的状态是才进行高回报率选择，否则使用最低回报率
			int ratioNum = (int) Math.floor(overall.getCurr_win_amount() / betAmount);
			int baseNum = 0;
			for(Integer ratio : ratioMap.keySet()){
				if(ratioNum >= baseNum && ratioNum < ratio){
					List<Integer> currList = ratioMap.get(ratio);
					//获取概率编号
					serialNum = getSerialNum(currList);
					break;
				}else{
					baseNum = ratio;
				}
			}
		}
		
		String symbolName = gameLevelInfo.getSymbol().get(serialNum);
		System.out.println("symbolName:" + symbolName);
		List<Map<Integer, ReelInfo>> reelList = gameLevelInfo.getReelList().get(symbolName);
		List<List<Integer>> reelSymbolIdList = gameLevelInfo.getReelSymbolIdList().get(symbolName);
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		
		List<Integer> currReel;//当前卷轴
//		for(int i = 0;i < reelSymbolIdList.size();++i){
//			List<Integer> allList = reelSymbolIdList.get(i);
//			int allNum = allList.size();
//			//记录已出现数字
//			List<Integer> recordNum = new ArrayList<Integer>();
//			
//			currReel = new ArrayList<Integer>();
//			Map<Integer, ReelInfo> currReelInfo = reelList.get(i);
//			Map<Integer, ReelInfo> recordReelInfo = new HashMap<Integer, ReelInfo>();
//			 
//			for(int j = 0;j < allNum;++j){
//				int thisNum = getRandomNum(recordNum, allNum);
//				recordNum.add(thisNum);
//			}
//			 
//			list.add(currReel);
//		}
		
//		for(int i = 0;i < reelSymbolIdList.size();++i){
//			List<Integer> allList = reelSymbolIdList.get(i);
//			
//			currReel = new ArrayList<Integer>();
//			Collections.shuffle(allList);
//			
//			//验证分散符不连续
//			Map<Integer, ReelInfo> reelMap = reelList.get(i);
//			ReelInfo reelInfo = reelMap.get(gameLevelInfo.getScatterId());
//			if (reelInfo.getNum() > 1 && gameLevelInfo.getScatterId() != -1) {
//				allList = verifiScatter(allList,gameLevelInfo.getScatterId());
//			}
//			
//			currReel.addAll(allList);
//			
//			list.add(currReel);
//		}
		
		return reelSymbolIdList;
	}
	private int getSerialNum(List<Integer> list){
		int num = 0;
		int base = 0;
		int randomNum = getRandomNum(null, 100);
		for(int i=0;i<list.size();++i){
			if(randomNum >= base && randomNum < base + list.get(i)){
				num = i;
				break;
			}else{
				base = base + list.get(i);
			}
		}
		return num;
	}
	private int getRandomNum(List<Integer> recordNum,int allNum){
		Random random = new Random();
		int thisNum = random.nextInt(allNum);
		
		if (recordNum != null && recordNum.contains(thisNum)) {
			thisNum = getRandomNum(recordNum,allNum);
		}
		
		return thisNum;
	}
	private List<Integer> verifiScatter(List<Integer> list,int scatterId){
		int allNum = list.size();
		
		List<Integer> recordNum = new ArrayList<Integer>();
		for(int i = 0;i < allNum;++i){
			if (list.get(i) == scatterId) {
				recordNum.add(i);
			}
		}
		
		for (int i = 0; i < recordNum.size(); i++) {
			list.remove(recordNum.get(i) - i);
		}
		int diff = (int) Math.floor(allNum / recordNum.size());
		int initW = 0;
		for (int i = 0; i < recordNum.size(); i++) {
			list.add(initW, scatterId);
			initW += diff;
		}
		
		
//		int firstPosition = -1;
//		int oldPosition = 0;
//		int newPosition = 0;
//		for (int i = 0; i < recordNum.size(); ++i) {
//			if (i == 0) {
//				firstPosition = recordNum.get(i);
//				if (firstPosition < 3) {
//					firstPosition = 3;
//					list.add(firstPosition, list.get(recordNum.get(i)));
//					list.remove(recordNum.get(i));
//				}
//				oldPosition = firstPosition;
//			}else{
//				newPosition = recordNum.get(i);
//				if ((newPosition - oldPosition) < 3 || (allNum - newPosition + firstPosition) < 3 ) {
//					int updateP = oldPosition + 4;
//					if (updateP > allNum) {
//						
//					}else{
//						list.add(updateP, list.get(recordNum.get(i)));
//						list.remove(newPosition);
//					}
//					
//					oldPosition = updateP;
//				}
//			}
//		}
		
		return list;
	}
	private List<List<Integer>> getSelectSymbol(List<List<Integer>> reelList){
		List<List<Integer>> showReel = new ArrayList<List<Integer>>();
		for(List<Integer> list : reelList){
			List<Integer> thisReel = new ArrayList<Integer>();
			int allNum = list.size();
			int sNum = getRandomNum(null, allNum);
			int fNum = sNum - 1;
			if(fNum < 0) fNum = allNum + fNum; 
			int tNum = sNum + 1;
			if(tNum == allNum) tNum = 0; 
			
			thisReel.add(list.get(fNum));
			thisReel.add(list.get(sNum));
			thisReel.add(list.get(tNum));
			
			System.out.println("Reel:" + list.get(fNum) + "|" + list.get(sNum) + "|" + list.get(tNum));
			showReel.add(thisReel);
		}
		
		return showReel;
	}
	private List<WinLineInfo> checkLine(List<List<Integer>> showReel,GameLevelInfo gameLevelInfo){
		List<WinLineInfo> lineList = new ArrayList<WinLineInfo>();
		List<LineInfo> lineInfos = dataManager.getLineMap(betLine);
		//处理分散符 lineNum = -1
		int scatNum = 0;
		for(List<Integer> list : showReel){
			if (list.contains(gameLevelInfo.getScatterId())) {
				scatNum++;
			}
		}
		lineList = addWinLineInfoList(gameLevelInfo.getScatterId(), scatNum, lineList,gameLevelInfo,-1);
		//---------------------------------------------------//
		for (int i = 0; i < lineInfos.size(); ++i) {
			LineInfo lineInfo = lineInfos.get(i);
			List<Integer> include = lineInfo.getInclude();

			int firstId = showReel.get(0).get(include.get(0));
			//分散符号跳过
			if (firstId == gameLevelInfo.getScatterId()) {
				continue;
			}
			//连续的wild
			int totalNum = 1;
			for (int j = 1; j < include.size(); ++j) {
				int currId = showReel.get(j).get(include.get(j));
				if(firstId == gameLevelInfo.getWildId() && currId == gameLevelInfo.getWildId()){
					totalNum++;
				}else{
					break;
				}
			}
			lineList = addWinLineInfoList(firstId, totalNum, lineList,gameLevelInfo,lineInfo.getId());
			//先取连续数--不包含Bonus
			if(firstId != gameLevelInfo.getBonusId()){
				totalNum = 1;
				for (int j = 1; j < include.size(); ++j) {
					int currId = showReel.get(j).get(include.get(j));
					//处理首个图标为wild
					if(firstId == gameLevelInfo.getWildId()){
						firstId = currId;
					}
					//当前id 不是首个符号id 并且不是野蛮符 跳出
					if (currId != firstId && currId != gameLevelInfo.getWildId()) {
						break;
					}else{
						totalNum++;
					}
				}
				//不计算Scatter
				if(firstId != gameLevelInfo.getScatterId())
					lineList = addWinLineInfoList(firstId, totalNum, lineList,gameLevelInfo,lineInfo.getId());
			}
			//计算Bonus--
			totalNum = 0;
			for (int j = 0; j < include.size(); ++j) {
				int currId = showReel.get(j).get(include.get(j));
				//处理首个图标为wild
				if(currId == gameLevelInfo.getBonusId()){
					totalNum++;
				}
				lineList = addWinLineInfoList(gameLevelInfo.getBonusId(), totalNum, lineList,gameLevelInfo,lineInfo.getId());
			}
			
			//处理前面全部都是野蛮符号的
//			if(firstId == gameLevelInfo.getWildId()){
//				firstId = showReel.get(totalNum).get(include.get(totalNum));
//				totalNum++;
//				lineList = addWinLineInfoList(firstId, totalNum, lineList,gameLevelInfo,lineInfo.getId());
//			}
			//---------------------------------------------------//
		}
		return lineList;
	}
	private List<WinLineInfo> addWinLineInfoList(int firstId,int totalNum,List<WinLineInfo> lineList,GameLevelInfo gameLevelInfo,int lineId){
		//取第一概率的图标信息，因为都是一样的
		String symbolName = gameLevelInfo.getSymbol().get(0);
		SymbolInfo symbolInfo = gameLevelInfo.getSymbolInfo().get(symbolName).get(firstId);
		List<SymbolReward> rewardList = symbolInfo.getReward();
		for(SymbolReward reward : rewardList){
			if(totalNum == reward.getNum()){
				WinLineInfo winLineInfo = new WinLineInfo();
				winLineInfo.setLineId(lineId);
				winLineInfo.setSymbolId(firstId);
				winLineInfo.setNum(totalNum);
				//如果是Bonus标记一下
				if(firstId == gameLevelInfo.getBonusId())
					winLineInfo.setIsBonus(1);
				else
					winLineInfo.setIsBonus(0);
				
				System.out.println("line:" + winLineInfo.getLineId() + ",symbolId:" + winLineInfo.getSymbolId() + ",num:" + winLineInfo.getNum() + "IsBonus:" + winLineInfo.getIsBonus());
				lineList.add(winLineInfo);
				//过滤scatter和Bonus
				if (lineId != -1) {
					if(winLineInfo.getIsBonus() != 1)
						payMult += reward.getMult();
				}else{//记录免费旋转的次数
					try {
						playerSystem.updateFreeTimes(gameLevelId,reward.getMult(),playerInfo);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return lineList;
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
