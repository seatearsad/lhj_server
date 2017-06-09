/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-6
 * @Description 
 */

package com.wolfroc.slots.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wolfroc.slots.message.settleMessage.SettleMessage;
import com.wolfroc.slots.servlet.main.AppContext;


public class MainServlet extends HttpServlet{
	private Logger logger = Logger.getLogger(getClass());
	private SettleMessage settleMessage;
	public void init() throws ServletException {
		super.init();
		settleMessage = AppContext.getInstance().getBean("settleMessage");
		logger.info("====MessageServlet====");
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//跨域访问
		resp.setHeader("Access-Control-Allow-Origin", "*");
		settleMessage.settle(req, resp);
		//logger.info(playerManager.getPlayerCountNum());
	}
}