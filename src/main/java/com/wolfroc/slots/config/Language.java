/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-10-8
 * @Description 
 */

package com.wolfroc.slots.config;

public class Language extends BaseConfig{
	private static String filePath = CoreConfig.getInstance().getConfig("language_path");
	private static Language language;
	public Language() {
		super(filePath);
	}
	public static Language getInstance(){
		if (language == null) {
			language = new Language();
		}
		return language;
	}

}
