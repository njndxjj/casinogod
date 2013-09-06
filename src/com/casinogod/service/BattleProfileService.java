package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casinogod.battle.GameType;
import com.casinogod.dao.BattleHistoryDAOImpl;
import com.casinogod.pojo.BattleHistory;

public class BattleProfileService implements  BattleProfile {
	
	
	private BattleHistoryDAOImpl battleHistoryDao;
	
	private BattleHistory battleHistory;
	
	

	public void setBattleHistoryDao(BattleHistoryDAOImpl battleHistoryDao) {
		this.battleHistoryDao = battleHistoryDao;
	}

	public void setBattleHistory(BattleHistory battleHistory) {
		this.battleHistory = battleHistory;
	}

	public List<BattleHistory> queryall() {
		// TODO Auto-generated method stub
		return battleHistoryDao.querAllHistory();
	}

	public boolean addBattleProfile(long battleId,long owenId, String userList,
			int battleStatue, int battleType, String gameType,String createTime, String result,
			String otherNotes,int level,int isSpecialWin) {
		// TODO Auto-generated method stub
		battleHistory.setBattleId(battleId);
		battleHistory.setOwenId(owenId);
		battleHistory.setUserList(userList);
		battleHistory.setBattleStatue(battleStatue);
		battleHistory.setBattleType(battleType);
		battleHistory.setGameType(gameType);
		battleHistory.setCreateTime(createTime);
		battleHistory.setResult(result);
		battleHistory.setOtherNotes(otherNotes);
		battleHistory.setLevel(level);
		battleHistory.setIsSpecialWin(isSpecialWin);
		
		battleHistoryDao.insertBattleHistory(battleHistory);
		
		return true;
	}

	public boolean thressRate(long ownId, int battleStatue,int battleType,String createTime,int n,int gameType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		users.put("createTime", createTime);
		users.put("endTime", createTime);
		users.put("gameType", GameType.values()[gameType].toString());
		List <BattleHistory> list=battleHistoryDao.userRate(users);
		
		if(list.size()>0&&list.size()>=n)
		{	
			for(int i=0;i<n;i++)
			{
				if(Integer.valueOf(list.get(i).getResult())<=0)
					return false;
			}
			
		}
		else
		{
			return false;
		}
		
		return true;
	}

	public int isSpecialWin(long ownId, int battleStatue, int battleType,
			String createTime, String endTime,int gameType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		users.put("gameType", GameType.values()[gameType].toString());
		return battleHistoryDao.userRank(users);
	}

	public List<BattleHistory> winBattle(long ownId, int battleStatue,
			int battleType, String createTime,String endTime,int gameType) {
		// TODO Auto-generated method stub
		
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		users.put("gameType", GameType.values()[gameType].toString());
		List <BattleHistory> list=battleHistoryDao.winTotal(users);
		return list;
	}

	public int totalReuslt(long ownId, int battleStatue, int battleType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		
		return battleHistoryDao.userTotalResult(users);
	}

	public int totalWinResult(long ownId, int battleStatue, int battleType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		return battleHistoryDao.userWinResult(users);
	}

	public int totalLoseResult(long ownId, int battleStatue, int battleType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		
		return battleHistoryDao.userLoseResult(users);
	}

	public int isSpecialWinNum(long ownId, int battleStatue, int battleType,
			String createTime, String endTime, int gameType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		users.put("gameType", GameType.values()[gameType].toString());
		return battleHistoryDao.userSpecialWin(users);
	}

	public int isSpecialLoseNum(long ownId, int battleStatue, int battleType,
			String createTime, String endTime, int gameType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		users.put("gameType", GameType.values()[gameType].toString());
		return battleHistoryDao.userSpecialLose(users);
	}

	public int isSpecialDrawNum(long ownId, int battleStatue, int battleType,
			String createTime, String endTime, int gameType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		users.put("gameType", GameType.values()[gameType].toString());
		return battleHistoryDao.userSpeciaDraw(users);
	}

	public int totalDrawNum(long ownId, int battleStatue, int battleType) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		
		return battleHistoryDao.userDrawNum(users);
	}

	public int totalWinNumt(long ownId, int battleStatue, int battleType
			) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
	
		
		return battleHistoryDao.userWinNum(users);
	}

	public int totalLoseNum(long ownId, int battleStatue, int battleType
			) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		
		return battleHistoryDao.userLoseNum(users);
	}

	public int totalReusltWithDate(long ownId, int battleStatue,
			int battleType,String createTime,String endTime) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);		
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		return battleHistoryDao.userTotalResultWithDate(users);
	}

	public int totalWinResultWithDate(long ownId, int battleStatue,
			int battleType,String createTime,String endTime) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);
		
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		return battleHistoryDao.userWinResultWithDate(users);
	}

	public int totalLoseResultWithDate(long ownId, int battleStatue,
			int battleType,String createTime,String endTime) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);	
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		return battleHistoryDao.userLoseResultWithDate(users);
	}

	public int totalDrawNumWithDate(long ownId, int battleStatue, int battleType,
			String createTime,String endTime) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);	
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		return battleHistoryDao.userDrawNumWithDate(users);
	}

	public int totalWinNumtWithDate(long ownId, int battleStatue,
			int battleType, String createTime, String endTime) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);	
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		return battleHistoryDao.userWinNumWithDate(users);
	}

	public int totalLoseNumWithDate(long ownId, int battleStatue,
			int battleType, String createTime, String endTime) {
		// TODO Auto-generated method stub
		Map<Object,Object> users=new HashMap<Object, Object>();
		users.put("owenId", ownId);
		users.put("battleStatue", battleStatue);
		users.put("battleType", battleType);	
		users.put("createTime", createTime);
		users.put("endTime", endTime);
		return battleHistoryDao.userLoseNumWithDate(users);
	}

	
	
	
	
	

}
