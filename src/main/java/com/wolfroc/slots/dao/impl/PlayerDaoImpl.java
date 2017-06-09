/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.dao.impl;

import java.util.List;

import com.wolfroc.slots.dao.BaseHibernateDao;
import com.wolfroc.slots.dao.PlayerDao;
import com.wolfroc.slots.exception.DaoException;
import com.wolfroc.slots.pojo.PlayerPojo;

public class PlayerDaoImpl extends BaseHibernateDao implements PlayerDao{

	@Override
	public PlayerPojo updatePlayer(PlayerPojo playerPojo) throws DaoException {
		try {
			return (PlayerPojo)update(playerPojo);
		} catch (Exception e) {
			throw new DaoException("",e);
		}
	}

	@Override
	public PlayerPojo getPlayerPojo(long playerId) throws DaoException {
		try {
			return (PlayerPojo)get(PlayerPojo.class, playerId);
		} catch (Exception e) {
			throw new DaoException("", e);
		}
	}

	@Override
	public PlayerPojo getPlayerPojoByUserId(int userId) throws DaoException {
		try {
			String sql = "from PlayerPojo c where c.userId = " + userId;
			List<?> pojos = find(sql);
			if(pojos == null || pojos.size() < 1){
				return null;
			}else{
				return (PlayerPojo)pojos.get(0);
			}
		}catch(Exception e){
			throw new DaoException("", e);
		}
	}

	@Override
	public PlayerPojo createPlayerPojo(PlayerPojo pojo) throws DaoException {
		try {
			return (PlayerPojo)save(pojo);
		} catch (Exception e) {
			throw new DaoException("",e);
		}
	}

}
