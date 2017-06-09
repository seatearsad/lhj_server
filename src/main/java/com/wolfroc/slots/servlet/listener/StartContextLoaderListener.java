/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-11
 * @Description 
 */

package com.wolfroc.slots.servlet.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wolfroc.slots.servlet.main.AppContext;

public class StartContextLoaderListener extends ContextLoaderListener{
	private Logger logger = Logger.getLogger(getClass());
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        AppContext.getInstance().setContext(context);
        logger.info("Slots Game Server StartContextLoaderListener started");
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {   
    	 super.contextDestroyed(event);
      } 
}
