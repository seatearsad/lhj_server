package com.wolfroc.slots.pojo;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by LangJian
 * @Date 2013-12-26 ����06:24:58
 * @Description 
 */
@SuppressWarnings("serial")
public class BasePojo implements Serializable{
	public Field[] getFields(Class<?> obj){
		Field[] field;
		if(obj.getName().equals("com.wolfroc.darkThree.pojo.BasePojo")){
			return obj.getDeclaredFields();
		}else if(obj.getName().equals("java.lang.Object")){
			return obj.getDeclaredFields();
		}else{
			Field[] superField=getFields(obj.getSuperclass());
			Field[] currField=obj.getDeclaredFields();
			field=new Field[superField.length+currField.length];
			System.arraycopy(superField,0,field,0,superField.length);
			System.arraycopy(currField,0,field,superField.length,currField.length);
			return field;
		}
	}
	
	public String toString(){
		Field[] fields=getFields(getClass());
		StringBuffer buffer=new StringBuffer(getClass().getSimpleName());
		buffer.append("[ ");
		for(int i=0;i<fields.length;i++){
			buffer.append(fields[i].getName()).append("=");
			try {
				String propertyName=fields[i].getName();
				PropertyDescriptor prop=new PropertyDescriptor(propertyName,this.getClass());
				Object result=prop.getReadMethod().invoke(this,new Object[0]);
				buffer.append(result);
			} catch (Exception e) {}
			buffer.append(" ");
		}
		buffer.append("]");
		return buffer.toString();
	}
}

