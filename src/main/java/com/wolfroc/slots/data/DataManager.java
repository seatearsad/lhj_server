/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-25
 * @Description 
 */

package com.wolfroc.slots.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.reflect.TypeToken;
import com.wolfroc.slots.Util.FileUtils;
import com.wolfroc.slots.Util.JsonManager;
import com.wolfroc.slots.data.game_level.GameLevelInfo;
import com.wolfroc.slots.data.game_level.ReelInfo;
import com.wolfroc.slots.data.line.LineInfo;
import com.wolfroc.slots.data.symbol.SymbolInfo;

public class DataManager {
	private Logger logger = Logger.getLogger(getClass());
	private Map<Integer, LineInfo> lineMap;
	private Map<Integer, GameLevelInfo> gameLevelMap;
	public void init(){
		logger.info("Data loading Start!");
		initLine();
		initGameLevel();
	}
	public void reloadData(){
		logger.info("Data Reloading!");
		init();
	}
	private void initLine(){
		String file = FileUtils.readFile(DataFileList.lineFile);
		Type type = new TypeToken<List<LineInfo>>() {}.getType();
		List<LineInfo> list = JsonManager.getGson().fromJson(file,type);
		
		lineMap = new HashMap<Integer, LineInfo>();
		for(LineInfo lineInfo:list)
		{
			lineMap.put(lineInfo.getId(), lineInfo);
		}
	}
	private void initGameLevel() {
		String file = FileUtils.readFile(DataFileList.gameLevelFile);
		Type type = new TypeToken<List<GameLevelInfo>>() {}.getType();
		List<GameLevelInfo> list = JsonManager.getGson().fromJson(file, type);
		gameLevelMap = new HashMap<Integer, GameLevelInfo>();
		for(GameLevelInfo gameLevelInfo:list){
			gameLevelInfo = setGameLevelSymbol(gameLevelInfo);
			gameLevelMap.put(gameLevelInfo.getId(), gameLevelInfo);
		}
	}
	private GameLevelInfo setGameLevelSymbol(GameLevelInfo gameLevelInfo){
		String filePath = DataFileList.root + "data/symbol/" + gameLevelInfo.getSymbol() +".json";
		String file = FileUtils.readFile(filePath);
		Type type = new TypeToken<List<SymbolInfo>>() {}.getType();
		List<SymbolInfo> list = JsonManager.getGson().fromJson(file, type);
		gameLevelInfo.setSymbolInfo(list);
		
		List<Map<Integer, ReelInfo>> reelInfos = new ArrayList<Map<Integer,ReelInfo>>();

		reelInfos.add(0, new HashMap<Integer, ReelInfo>());
		reelInfos.add(1, new HashMap<Integer, ReelInfo>());
		reelInfos.add(2, new HashMap<Integer, ReelInfo>());
		reelInfos.add(3, new HashMap<Integer, ReelInfo>());
		reelInfos.add(4, new HashMap<Integer, ReelInfo>());
		
		int symbolNum = 0;
		
		List<List<Integer>> reelSymbolIdList = new ArrayList<List<Integer>>();
		reelSymbolIdList.add(0,new ArrayList<Integer>());
		reelSymbolIdList.add(1,new ArrayList<Integer>());
		reelSymbolIdList.add(2,new ArrayList<Integer>());
		reelSymbolIdList.add(3,new ArrayList<Integer>());
		reelSymbolIdList.add(4,new ArrayList<Integer>());
		
		int scatterId = -1;
		int wildId = -1;
		
		for(SymbolInfo symbolInfo : list){
			List<Integer> weight = symbolInfo.getWeight();
			for(int i = 0;i<weight.size();++i){
				ReelInfo info = new ReelInfo();
				info.setSymId(symbolInfo.getId());
				info.setNum(weight.get(i));
				info.setType(symbolInfo.getType());
				
				reelInfos.get(i).put(info.getSymId(), info);
				
//				for(int j = 0;j < weight.get(i);++j){
//					reelSymbolIdList.get(i).add(symbolInfo.getId());
//				}
				
				symbolNum += weight.get(i);
			}
			
			if (scatterId == -1 && symbolInfo.getType() == 2) {
				scatterId = symbolInfo.getId();
			}
			if (wildId == -1 && symbolInfo.getType() == 1) {
				wildId = symbolInfo.getId();
			}
		}
		
		//固定排列设置
		int i = 0;
		for(Map<Integer, ReelInfo> infoMap : reelInfos){
			int gi = 0;
			for (Integer key : infoMap.keySet()) {
				ReelInfo info = infoMap.get(key);
				if (gi == 0) {
					for (int j = 0; j < info.getNum(); j++) {
						reelSymbolIdList.get(i).add(key);
					}
				}else{
					int interval = (int) Math.floor(reelSymbolIdList.get(i).size() / info.getNum());
					int index = 0;
					int baseNum = 0;
					if (interval == 0) {
						interval = 1;
					}else{
						baseNum = reelSymbolIdList.get(i).size() % info.getNum();
					}
					for (int j = 0; j < info.getNum(); j++) {
						if (interval < 2) {
							index = interval * j + j;
						}else{
							index = baseNum + interval * j;
						}
						
						if (index > reelSymbolIdList.get(i).size()) {
							index = reelSymbolIdList.get(i).size();
						}
						reelSymbolIdList.get(i).add(index, key);
					}
				}
				++gi;
			}
			++i;
		}
		gameLevelInfo.setReelList(reelInfos);
		gameLevelInfo.setSymbolNum(symbolNum);
		gameLevelInfo.setReelSymbolIdList(reelSymbolIdList);
		gameLevelInfo.setScatterId(scatterId);
		gameLevelInfo.setWildId(wildId);
		
		return gameLevelInfo;
	}
	public GameLevelInfo getGameLevelInfo(int id){
		GameLevelInfo gameLevelInfo = gameLevelMap.get(id);
		return gameLevelInfo;
	}
	public List<LineInfo> getLineMap(int num){
		List<LineInfo> lineList = new ArrayList<LineInfo>();
		for (int i = 0; i < num; i++) {
			lineList.add(lineMap.get(i));
		}
		return lineList;
	}
}
