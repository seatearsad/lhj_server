package com.wolfroc.slots.Util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2012-8-1 上午11:40:50
 * @Description 
 */
public class JsonManager {

	private static Gson gson;

	private static GsonBuilder gsonBuilder;
	
	static 
	{
		gson = new Gson();
		gsonBuilder = new GsonBuilder();
	}
	
	public static Gson getGson()
	{
		return gson;
	}
	
	public static GsonBuilder getGsonBuilder()
	{
		return gsonBuilder;
	}
//	@SuppressWarnings("unchecked")
//	public static <T> List<T> getListFromJson(List<?> list, Class<T> c,String file) {
//		Type type = new TypeToken<List<T>>() {}.getType();
//		list = JsonManager.getGson().fromJson(file,type);
//		return (List<T>) list;
//	}
}
