/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-12
 * @Description 
 */

package com.wolfroc.slots.application.overall;

import com.wolfroc.slots.pojo.OverallPojo;

public interface OverallManager {
	public void start() throws Exception;
	public void stop() throws Exception;
	//获取全局信息
	public OverallPojo getOverallPojo() throws Exception;
	//更新全局信息
	public void updateOverall(int betAmount,int payAmount,boolean isWin) throws Exception;
	//更新骰子游戏全局信息
	public void updateDiceOverall(int bet,boolean isWin)throws Exception;
}
