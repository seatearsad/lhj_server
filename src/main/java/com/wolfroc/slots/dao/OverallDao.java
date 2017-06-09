/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-12
 * @Description 
 */

package com.wolfroc.slots.dao;

import com.wolfroc.slots.exception.DaoException;
import com.wolfroc.slots.pojo.OverallPojo;

public interface OverallDao {
	public OverallPojo getOverallPojo() throws DaoException;
	public OverallPojo updateOverall(OverallPojo pojo) throws DaoException;
}
