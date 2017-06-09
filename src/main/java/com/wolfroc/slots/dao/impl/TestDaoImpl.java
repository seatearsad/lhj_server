/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-5
 * @Description 
 */

package com.wolfroc.slots.dao.impl;

import com.wolfroc.slots.dao.BaseHibernateDao;
import com.wolfroc.slots.dao.TestDao;
import com.wolfroc.slots.exception.DaoException;
import com.wolfroc.slots.pojo.TestPojo;

public class TestDaoImpl extends BaseHibernateDao implements TestDao{

	@Override
	public TestPojo getTestPojo(int id) throws DaoException {
		try {
			return (TestPojo)get(TestPojo.class, id);
		} catch (Exception e) {
			throw new DaoException("",e);
		}
	}

}
