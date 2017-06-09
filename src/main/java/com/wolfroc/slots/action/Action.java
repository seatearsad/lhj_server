/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-17
 * @Description 
 */

package com.wolfroc.slots.action;

import com.wolfroc.slots.message.RequestMessage;
import com.wolfroc.slots.message.ResponseMessage;


public abstract class Action {
	public abstract String init(RequestMessage requestMessage,ResponseMessage responseMessage) throws Exception;
}
