package com.wolfroc.slots.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by LangJian
 * @Date 2013-10-12 06:10:16
 * @Description 
 */
public abstract class BaseConfig implements Configurable{
	
	private long lastModified = 0L;
	protected File sourceFile;
	protected Properties prop;
	
	public BaseConfig(String filePath){
		sourceFile = new File(this.getClass().getResource("/").getPath()+filePath);
		prop = new Properties();
		load();
	}
	
	private void load() {
		try {
			prop.load(new FileInputStream(sourceFile));
			lastModified = System.currentTimeMillis();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getConfig(String key) {
		if(sourceFile.lastModified()>lastModified){
			load();
		}
		return prop.getProperty(key);
	}
	public String toString(){
		StringBuffer str = new StringBuffer();
		Enumeration<Object> keys = prop.keys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement().toString();
			String value = prop.getProperty(key);
			str.append("{").append(key).append("=").append(value).append("}");
		}
		return str.toString();
	}
}
