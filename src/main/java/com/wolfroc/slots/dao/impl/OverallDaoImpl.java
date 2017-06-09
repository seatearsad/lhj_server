/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-12
 * @Description 
 */

package com.wolfroc.slots.dao.impl;

import com.wolfroc.slots.dao.BaseHibernateDao;
import com.wolfroc.slots.dao.OverallDao;
import com.wolfroc.slots.exception.DaoException;
import com.wolfroc.slots.pojo.OverallPojo;

public class OverallDaoImpl extends BaseHibernateDao implements OverallDao{

	@Override
	public OverallPojo getOverallPojo() throws DaoException {
		try {
			OverallPojo overallPojo = (OverallPojo)loadAll(OverallPojo.class).get(0);
			return overallPojo;
		} catch (Exception e) {
			throw new DaoException("",e);
		}
	}

	@Override
	public OverallPojo updateOverall(OverallPojo pojo) throws DaoException {
		try {
			return (OverallPojo)update(pojo);
		} catch (Exception e) {
			throw new DaoException("",e);
		}
	}

}
