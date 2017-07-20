/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-21
 * @Description 
 */

package com.wolfroc.slots.data;

import com.wolfroc.slots.servlet.MainServlet;


public class DataFileList {
	public static String root = MainServlet.class.getResource("/").toString();
	public static final String lineFile = root + "data/line/line.json";
	public static final String gameLevelFile = root + "data/game_level/game_level.json";
	public static final String symbol_1 = root + "data/symbol/symbol_1.json";
	public static final String ratioFile = root + "data/game_level/ratio.json";
	public static final String diceFile = root + "data/game_level/dice.json";
}
