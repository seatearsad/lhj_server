/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2017-5-19
 * @Description 
 */

package com.wolfroc.slots.dao;

import com.wolfroc.slots.exception.DaoException;
import com.wolfroc.slots.pojo.PlayerPojo;

public interface PlayerDao {
	public PlayerPojo getPlayerPojoByUserId(int userId)throws DaoException;
	public PlayerPojo updatePlayer(PlayerPojo playerPojo) throws DaoException;
	public PlayerPojo getPlayerPojo(long playerId)throws DaoException;
	public PlayerPojo createPlayerPojo(PlayerPojo pojo)throws DaoException;
}
