/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-12
 * @Description 
 */

package com.wolfroc.slots.message.settleMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wolfroc.slots.Util.JsonManager;
import com.wolfroc.slots.config.Language;
import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;
import com.wolfroc.slots.message.ResultCode;
import com.wolfroc.slots.message.error.CommonErrorResp;
import com.wolfroc.slots.servlet.main.ActionDispatcher;
import com.wolfroc.slots.servlet.main.AppContext;


public class SettleMessage{
	private Logger logger = Logger.getLogger(getClass());
//	private PlayerSystem playerSystem;

	public SettleMessage(){
		logger.info("settleMessage");
	}
	
	public void settle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String data = init(request);
		out.println(data);
	}
	public String init(HttpServletRequest request) throws IOException{
		String data = "";
		String messageId = "";
		String playerId = "";
		String serverId = "";
		String remoteAddr = "";
		String url = "";
		String sign = "";
		
		try {
			remoteAddr = request.getRemoteAddr();
			messageId = request.getParameter("mid");
			playerId = request.getParameter("pid");
			serverId = request.getParameter("sid");
			data = request.getParameter("data");
			data = URLDecoder.decode(data,"utf8");
			data=new String(data.getBytes("ISO8859-1"),"utf8");
			url = request.getRequestURL().toString();
			sign = request.getParameter("sign");
			
			String respMessageId = "6" + messageId;
			
			int mId = Integer.parseInt(messageId);
			int pId = Integer.parseInt(playerId);
			int sId = Integer.parseInt(serverId);
			
			StringBuffer str = new StringBuffer();
			str.append("messageId=").append(mId).append("&");
			str.append("playerId=").append(pId).append("&");
			str.append("serverId=").append(sId).append("&");
			str.append("data=").append(data).append("&");
			str.append("sign=").append(sign).append("&");
			str.append("remoteAddr=").append(remoteAddr).append("&");
			str.append("url=").append(url);
			
			logger.info("==Request:" + str.toString() + "==");
			RequestMessage requestMessage = getRequestMessage(messageId,pId,sId,data);
			if(requestMessage == null){
				return new CommonErrorResp(Language.getInstance().getConfig("messageError"),ResultCode.seriousErrorCode).getData();
			}
			
			ResponseMessage responseMessage = getResponseMessage(respMessageId);
			String respData = ActionDispatcher.getInstance().dispatcher(requestMessage, responseMessage);
			if(respData == null){
				throw new IOException("Message Error messageId：" + messageId);
			}
			return respData;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private RequestMessage getRequestMessage(String messageId,int playerId,int serverId,String data){
		RequestMessage requestMessage = AppContext.getInstance().getBean(messageId);
		requestMessage = JsonManager.getGson().fromJson(data, requestMessage.getClass());
		requestMessage.setMessageId(Integer.valueOf(messageId));
		requestMessage.setData(playerId, serverId, data);
		//用户及loginKey验证
		boolean isLoginKey = checkLoginKey(requestMessage);
		if (!isLoginKey) {
			return null;
		}
		return requestMessage;
	}
	
	private ResponseMessage getResponseMessage(String respMessageId){
		ResponseMessage responseMessage = AppContext.getInstance().getBean(respMessageId);
		return responseMessage;
	}
	private boolean checkLoginKey(RequestMessage requestMessage){
		String loginKey = requestMessage.getLoginKey();
		if (requestMessage.getMessageId() != 1001 && requestMessage.getMessageId() != 1002) {
//			PlayerBaseInfo playerBaseInfo = null;
//			try {
//				playerBaseInfo = playerSystem.getPlayerBaseInfoByPlayerId(requestMessage.getPlayerId());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if (playerBaseInfo == null) {
//				return false;
//			}
//			if (playerBaseInfo.getLoginKey() == null || !playerBaseInfo.getLoginKey().equals(loginKey)) {
//				return false;
//			}
			//重新加载缓存
//			if (playerBaseInfo.isClear()) {
//				try {
//					logger.info("loadCache:" + playerBaseInfo.getName());
//					playerSystem.addPlayerToMap(playerBaseInfo);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
		}
		
		return true;
	}
//	public PlayerSystem getPlayerSystem() {
//		return playerSystem;
//	}
//
//	public void setPlayerSystem(PlayerSystem playerSystem) {
//		this.playerSystem = playerSystem;
//	}
}
