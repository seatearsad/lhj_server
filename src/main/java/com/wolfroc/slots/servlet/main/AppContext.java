/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-11
 * @Description 
 */

package com.wolfroc.slots.servlet.main;

import org.springframework.context.ApplicationContext;



public class AppContext {
	private ApplicationContext context;
	private static AppContext instance;
	private AppContext() {
		super();
	}
	public static synchronized AppContext getInstance(){
		if(instance==null){
			instance=new AppContext();
		}
		return instance;
	}
	
	private ApplicationContext getApplicationContext(){
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName){
		return (T)getApplicationContext().getBean(beanName);
	}
}
