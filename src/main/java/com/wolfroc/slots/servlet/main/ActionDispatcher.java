/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-17
 * @Description 
 */

package com.wolfroc.slots.servlet.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.wolfroc.slots.action.Action;
import com.wolfroc.slots.config.CoreConfig;
import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;




public class ActionDispatcher {
	private final Logger logger = Logger.getLogger(getClass());
	private Map<String,String> actionMap=new HashMap<String,String>();
	private static ActionDispatcher dispatcher;
	private ActionDispatcher(){
		super();
		init();
	}
	
	public static ActionDispatcher getInstance(){
		if(dispatcher==null){
			dispatcher = new ActionDispatcher();
		}
		return dispatcher;
	}
	
	private void init(){
		InputStream input=null;
		try {
			final ClassLoader loader =getClass().getClassLoader();
			String actionPath = CoreConfig.getInstance().getConfig("action_path");
			URL url = loader.getResource(actionPath);
			input = url.openStream();
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(input, new DefaultHandler(){
				public void startElement(String uri, String localName, String qName,
						Attributes attributes) throws SAXException {
					if(qName.equals("action-map")){
						
					}else if(qName.equals("action")){
						try {
							String name=attributes.getValue("name");
							String beanName=attributes.getValue("bean");
							actionMap.put(name,beanName);
						} catch (Exception e) {
							logger.error("initialize action exception",e);
						}
					}
				}
			});
		} catch (Exception e) {
			logger.error("Initialize ActionDispatcher Exception",e);
		} finally {
			try {
				if(input != null)
					input.close();
			} catch (IOException e) {
				logger.error("IOException while closing configuration input stream. Error was "+ e.getMessage());
			}
		}
	}
	
	public String dispatcher(RequestMessage request,ResponseMessage response) {
		try {
			String beanName=(String)actionMap.get(String.valueOf(request.getMessageId()));
			Action action = (Action)AppContext.getInstance().getBean(beanName);
			String data = action.init(request, response);
			return data;
		} catch (Exception e) {
			logger.error("dispatcher exception",e);
			return null;
		}
	}
}
