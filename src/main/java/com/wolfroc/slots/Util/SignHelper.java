package com.wolfroc.slots.Util;



/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by LangJian
 * @Date 2013-12-23 ����05:27:28
 * @Description 
 */
public class SignHelper {
	public static String getSign(String command,String cpId,String productId,String data,String secretKey){
		String str = command+cpId+productId+data+secretKey;
		str=MD5.MD5Encode(str).toLowerCase();
		return str;
	}
}

