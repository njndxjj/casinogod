package com.casinogod.dao;


import java.util.List;
import java.util.Map;

import com.casinogod.pojo.BattleHistory;

public interface BattleHistoryDAO {
   
//	public List <BattleHistory> querUserName(String name);
//	
//	public List <User> quserUserId(long id);
//	
	public List <BattleHistory> querAllHistory();
	
	public List <BattleHistory> userRate(Map<Object,Object> ids);
	
	public List <BattleHistory> winTotal(Map<Object,Object> ids);

	public int userRank(Map<Object,Object> ids);
	
	public int userSpecialWin(Map<Object,Object> ids);
	
	public int userSpecialLose(Map<Object,Object> ids);
	
	public int userSpeciaDraw(Map<Object,Object> ids);
	
	public int userTotalResult(Map<Object,Object> ids);
	
	public int userWinResult(Map<Object,Object> ids);
	
	public int userLoseResult(Map<Object,Object> ids);
	
	public int userTotalResultWithDate(Map<Object,Object> ids);
	
	public int userWinResultWithDate(Map<Object,Object> ids);
	
	public int userLoseResultWithDate(Map<Object,Object> ids);
	
	public int userDrawNum(Map<Object,Object> ids);
	
	public int userDrawNumWithDate(Map<Object,Object> ids);
	
	public int userWinNum(Map<Object,Object> ids);
	
	public int userWinNumWithDate(Map<Object,Object> ids);
	
	public int userLoseNum(Map<Object,Object> ids);
	
	public int userLoseNumWithDate(Map<Object,Object> ids);
	
	public void insertBattleHistory(BattleHistory battleHistory);
	
	
}
