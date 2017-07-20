/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-12
 * @Description 
 */

package com.wolfroc.slots.application.overall;

import com.wolfroc.slots.dao.OverallDao;
import com.wolfroc.slots.pojo.OverallPojo;

public class OverallManagerImpl implements OverallManager{
	private OverallDao overallDao;
	private OverallPojo overall;
	@Override
	public void start() throws Exception {
		overall = overallDao.getOverallPojo();
	}
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public OverallPojo getOverallPojo() throws Exception {
		if (overall == null) {
			overall = overallDao.getOverallPojo();
		}
		
		return overall;
	}
	@Override
	public void updateOverall(int betAmount, int payAmount,boolean isWin) throws Exception {
		//赢的差
		int diff = betAmount - payAmount;
		overall.setCurr_total_amount(overall.getCurr_total_amount() + diff);
		overall.setCurr_win_amount(overall.getCurr_win_amount() + diff);
		if (isWin) {
			overall.setPlayer_win_round(overall.getPlayer_win_round() + 1);
		}
		overall.setTotal_round(overall.getTotal_round() + 1);
		
		overallDao.updateOverall(overall);
	}
	@Override
	public void updateDiceOverall(int bet, boolean isWin) throws Exception {
		overall.setTotal_dice_round(overall.getTotal_dice_round() + 1);
		if (isWin) {
			overall.setCurr_total_amount(overall.getCurr_total_amount() + bet);
			overall.setCurr_win_amount(overall.getCurr_win_amount() + bet);
			overall.setPlayer_dice_win_round(overall.getPlayer_dice_win_round() + 1);
		}else{
			overall.setCurr_total_amount(overall.getCurr_total_amount() - bet);
			overall.setCurr_win_amount(overall.getCurr_win_amount() - bet);
		}
		
		overallDao.updateOverall(overall);
	}
	public OverallDao getOverallDao() {
		return overallDao;
	}
	public void setOverallDao(OverallDao overallDao) {
		this.overallDao = overallDao;
	}
	public OverallPojo getOverall() {
		return overall;
	}
	public void setOverall(OverallPojo overall) {
		this.overall = overall;
	}
}
