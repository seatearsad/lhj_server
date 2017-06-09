package com.wolfroc.slots.Util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import com.wolfroc.slots.exception.ServiceException;


/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by LangJian
 * @Date 2013-12-18 下午02:03:41
 * @Description 
 */
public class HttpHelper {
	public static String sendHttpGetRequest(String url, Map<String,String> params)throws ServiceException {
		HttpURLConnection connection =null;
        BufferedReader in = null;
        try {
        	/**构建参数*/
        	String paramstr = null;
        	if(params!=null&&params.size()>0){
        		StringBuffer temp = new StringBuffer();
        		Iterator<String> it = params.keySet().iterator();
        		while(it.hasNext()){
        			String key = it.next();
        			String value = params.get(key);
        			temp.append(key).append("=").append(URLEncoder.encode(value,"utf-8")).append("&");
        		}
        		paramstr=temp.substring(0, temp.lastIndexOf("&"));
        	}
        	
        	String urlString = url + "?" + paramstr;
        	System.out.println("urlString="+urlString);
            URL realUrl = new URL(urlString);
            connection = (HttpURLConnection) realUrl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.connect();
            
            /**读数据*/
            StringBuffer result = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
            	result.append(line);
            }
            return result.toString();
        }catch (ConnectException e) {
        	throw new ServiceException("===sendHttpGetRequest 连接超时===");
        }catch(Exception e) {
            throw new ServiceException("===sendHttpGetRequest 出现异常===");
        }
        finally{
            try{
                if(in != null) {
                    in.close();
                }
                if(connection!=null){
                	connection.disconnect();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
	}

	public static String sendHttpPostRequest(String url, Map<String,String> params)throws ServiceException {
		HttpURLConnection connection = null;
        BufferedReader in = null;
        DataOutputStream out = null;
        try {
        	/**构建参数*/
        	String paramstr = null;
        	if(params!=null&&params.size()>0){
        		StringBuffer temp = new StringBuffer();
        		Iterator<String> it = params.keySet().iterator();
        		while(it.hasNext()){
        			String key = it.next();
        			String value = params.get(key);
        			temp.append(key).append("=").append(URLEncoder.encode(value, "utf-8")).append("&");
        			//temp.append(key).append("=").append(value).append("&");
        		}
        		paramstr=temp.substring(0, temp.lastIndexOf("&"));
        	}
        	System.out.println("url============"+url);
		    URL realUrl = new URL(url);
			connection = (HttpURLConnection) realUrl.openConnection();
		    connection.setDoOutput(true);
		    connection.setDoInput(true);
		    connection.setUseCaches(false);
		    connection.setRequestMethod("POST");
		    connection.setInstanceFollowRedirects(true);
		    connection.setRequestProperty("accept", "*/*");
		    connection.setRequestProperty("connection", "Keep-Alive");
		    connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		    connection.setRequestProperty("Charset", "UTF-8");
		    connection.connect();
		    
		    /**写数据*/
		    out = new DataOutputStream(connection.getOutputStream());
		    out.writeBytes(paramstr);
		    out.flush();
		    
		    /**读数据*/
		    StringBuffer result = new StringBuffer();
		    in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		    String line="";
		    while ((line = in.readLine()) != null) {
		       result.append(line);
		    }
		    return result.toString();
        }catch (ConnectException e) {
        	throw new ServiceException("===sendHttpPostRequest 连接超时===");
        }catch(Exception e) {
        	e.printStackTrace();
            throw new ServiceException("===sendHttpPostRequest 出现异常===");
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
                if(connection!=null){
                	connection.disconnect();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
	}
}

