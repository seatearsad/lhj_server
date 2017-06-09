package com.wolfroc.slots.config;


/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by LangJian
 * @Date 2014-2-24 09:02:28
 * @Description 
 */
public class CoreConfig extends BaseConfig{
	private final static String FILE_PATH = "config.properties";
	private static CoreConfig coreConfig;
	private CoreConfig(){
		super(FILE_PATH);
	}
	public static CoreConfig getInstance(){
		if(coreConfig == null){
			coreConfig = new CoreConfig();
		}
		return coreConfig;
	}
}

