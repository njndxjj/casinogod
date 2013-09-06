package com.casinogod.dao;


import java.util.List;
import java.util.Map;

import com.casinogod.pojo.BattleHistory;
import com.casinogod.pojo.UserBattleResult;

public interface UserBattleResultDAO {
   
	
	public List <UserBattleResult> querAll();
	
	public List <UserBattleResult> queryUserAndBattle(Map<Object,Object> ids);
	
	public List <UserBattleResult> queryUserAndGame(Map<Object,Object> ids);
	
	public List <UserBattleResult> queryUserAndGameAndBattle(Map<Object,Object> ids);
		
	public void insertBattleResult(UserBattleResult userBattleResult);
	
	public boolean updateBattleResult(UserBattleResult userBattleResult);
	
	
}
