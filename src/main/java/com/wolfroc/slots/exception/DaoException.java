package com.wolfroc.slots.exception;
/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by LangJian
 * @Date 2013-11-18 ����04:35:09
 * @Description 
 */
public class DaoException extends NestedCheckedException{

	private static final long serialVersionUID = 1L;
	public DaoException() {
		super("Dao Exception");
	}
	public DaoException(String msg) {
		super(msg);
	}
	public DaoException(String msg,Throwable e) {
		super(msg,e);
	}
	public String getSimpleMessage(){
		return getMessage().substring(getMessage().lastIndexOf(":")+1,getMessage().length());
	}
}
