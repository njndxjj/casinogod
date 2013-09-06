package com.casinogod.utility;

import java.util.HashMap;
import java.util.TreeMap;


public class DataStore {

	public static HashMap<String, String> cacheDataMap = new HashMap<String, String>();
	public static TreeMap<String, String> waitingBattle = new TreeMap<String, String>();
	public static TreeMap<String, String> fightingBattle = new TreeMap<String, String>();
	public static TreeMap<String, String> battlePlayers = new TreeMap<String, String>();
	public static TreeMap<Object, Object> registerSex = new TreeMap<Object, Object>();
	public static TreeMap<String, String> registerToken = new TreeMap<String, String>();
	public static TreeMap<String, Object> battePrize = new TreeMap<String, Object>();
	public static TreeMap<String, Object> setting = new TreeMap<String, Object>();
	
	public static void cleanCacheData()
	{
		cacheDataMap.clear();
		waitingBattle.clear();
		fightingBattle.clear();
		battlePlayers.clear();
	}
}
