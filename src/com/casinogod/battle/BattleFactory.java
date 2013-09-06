package com.casinogod.battle;

import game.casinogod.games.BlackJack;

import java.util.Iterator;

import net.sf.json.JSONObject;

import com.casinogod.pojo.BossBattleInfo;
import com.casinogod.utility.DataStore;
import com.casinogod.utility.Utility;

public class BattleFactory {
	
	private static String battleIDPrefix = "BattleID:";
	private static String playerIDPrefix = "PlayerID:";
	private static long dealerID = 0;
	
	private static Object sync_lock = new Object();
        
	public static BattleInfo createBattle(long userID, int battleType, int gameType, long battleId,
			long battlePrize,BossBattleInfo bossBattleInfo)
	{
		synchronized(sync_lock)
		{
			BattleInfo bat = null;
			if(battleType == 4)
			{
				
			}
			else if(battleType==3)
			{
				bat = new BattleInfo(userID, battleType, gameType,battlePrize,battleId);
				
				bat.setBattleCreateTime(Utility.getNowString());
				
				bat.setBossBattleInfo(bossBattleInfo);
				
				savePlayerInfo(userID,battlePrize);
				
				bat.setBattleStatus(1);
				
				saveFightingBattleInfo(bat);
				
				startFighting(bat);
			}
			else if(battleType == 1)
			{
				bat = new BattleInfo(userID, battleType, gameType,battlePrize,battleId);
				
				bat.setBattleCreateTime(Utility.getNowString());
				
				savePlayerInfo(userID,battlePrize);
				
				bat.setBattleStatus(1);
				
				saveFightingBattleInfo(bat);
				
				startFighting(bat);
			}
			else if(battleType == 2)
			{
				bat = mappingBattle(battleType, battlePrize);
				
				if(null ==  bat)
				{
					bat = new BattleInfo(userID, battleType, gameType, battlePrize,battleId);
					saveWaitingBattleInfo(bat);
				}
				else
				{
					deleteWaitingBattleInfo(bat.getBattleID());
					
					bat.setBattleCreateTime(Utility.getNowString());
					bat.setBattlePlayerUserIDList(new long[] {userID});
					
					//TODO
					//
					saveFightingBattleInfo(bat);
				}
			}
			return bat;
		}
	}
	
	
	
	
	public static void endBattle(long battleID)
	{
		synchronized(sync_lock)
		{
			deleteWaitingBattleInfo(battleID);
			deleteFightingBattleInfo(battleID);	
		}
	}

	private static BattleInfo mappingBattle(int battleType, long battlePrize)
	{
		BattleInfo battleOld = null;
		Iterator<String> iterator_2 = DataStore.waitingBattle.keySet().iterator();
		while (iterator_2.hasNext()) {
			JSONObject jsonObj = JSONObject.fromObject(DataStore.waitingBattle.get(iterator_2.next()));
			battleOld = (BattleInfo)JSONObject.toBean(jsonObj,BattleInfo.class);
			if(battleOld.getBattleType() == battleType
				&& battleOld.getBattlePrize() == battlePrize)
			{
				return battleOld;
			}
		}
		  
		return null;
	}
	
	
	
	
	

	private static void saveWaitingBattleInfo(BattleInfo battleInfo)
	{
		DataStore.waitingBattle.put(battleIDPrefix + battleInfo.getBattleID(),  
				JSONObject.fromObject(battleInfo).toString());
	}
	
	private static void deleteWaitingBattleInfo(long battleID)
	{
		if(DataStore.waitingBattle.containsKey(battleIDPrefix + battleID))
		{
			DataStore.waitingBattle.remove(battleIDPrefix + battleID);
		}
	}
	
	private static void saveFightingBattleInfo(BattleInfo battleInfo)
	{
		DataStore.fightingBattle.put(battleIDPrefix + battleInfo.getBattleID(),  
				JSONObject.fromObject(battleInfo).toString());
	}
	
	private static void deleteFightingBattleInfo(long battleID)
	{
		if(DataStore.waitingBattle.containsKey(battleIDPrefix + battleID))
		{
			DataStore.waitingBattle.remove(battleIDPrefix + battleID);
		}
	}
	
	public static PlayerInfo getPlayerInfo(long playerID)
	{	
		if(DataStore.battlePlayers.containsKey(playerIDPrefix + playerID))
		{
			JSONObject jsonObject = JSONObject.fromObject( DataStore.battlePlayers.get(playerIDPrefix + playerID) );  

	        return (PlayerInfo)JSONObject.toBean(jsonObject,PlayerInfo.class);
		}
		return null;
	}
	
	private static PlayerInfo savePlayerInfo(long playerID,long playerBet)
	{
		PlayerInfo player = new PlayerInfo();
		player.setPlayerID(playerID);
		player.setPlayerBet(playerBet);
		DataStore.battlePlayers.put(playerIDPrefix + playerID,  
				JSONObject.fromObject(player).toString());
		return player;
	}
	
	public static void updatePlayerInfo(PlayerInfo player)
	{
		if(DataStore.battlePlayers.containsKey(playerIDPrefix + player.getPlayerID()))
		{
			DataStore.battlePlayers.put(playerIDPrefix + player.getPlayerID(),  
					JSONObject.fromObject(player).toString());
		}
	}
	
	private static void deletePlayerInfo(PlayerInfo player)
	{
		if(DataStore.battlePlayers.containsKey(playerIDPrefix + player.getPlayerID()))
		{
			DataStore.battlePlayers.remove(playerIDPrefix + player.getPlayerID());
		}
	}
	
	private static void startFighting(BattleInfo batFighting)
	{
		switch(batFighting.getGameType())
		{
			case 0:
				BlackJack.startGame(batFighting);
				break;
			case 4:
				break;
				
			case 1:
				break;
				
			case 2:
				BlackJack.startGame(batFighting);
				break;	
				
			default:
				BlackJack.startGame(batFighting);
				break;
		}
	
	}
}
