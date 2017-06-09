/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-5
 * @Description 
 */

package com.wolfroc.slots.dao;

import com.wolfroc.slots.exception.DaoException;
import com.wolfroc.slots.pojo.TestPojo;




public interface TestDao {
	/**
	 * ªÒ»°
	 */
	public TestPojo getTestPojo(int id)throws DaoException;
}
