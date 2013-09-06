package com.casinogod.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.UserBattleHistory;

public class UserBattleHistoryDAOImpl extends SqlMapClientDaoSupport implements UserBattleHistoryDAO {

	public List<UserBattleHistory> quserUser(long userId, String gameType) {
		// TODO Auto-generated method stub
		HashMap para=new HashMap();
		para.put("userId", userId);
		para.put("gameType",gameType);
		List list=null;
		list=this.getSqlMapClientTemplate().queryForList("userBattleHistory.selectUser", para);
		return list;
	}

	public List<UserBattleHistory> querAll() {
		// TODO Auto-generated method stub
		List list=null;
		list=this.getSqlMapClientTemplate().queryForList("userBattleHistory.findAll");
		return list;
	}

	public boolean updateUser(long userId, String gameType,int winTimes, int lostTime, long winMoney,
			long lostMoney,int drawTimes) {
		// TODO Auto-generated method stub
		
		int updatecount=1;
		HashMap para=new HashMap();
		para.put("userId", userId);
		para.put("gameType",gameType);
		para.put("winTimes",winTimes);
		para.put("lostTime",lostTime);
		para.put("winMoney",winMoney);
		para.put("lostMoney",lostMoney);
		para.put("drawTimes",drawTimes);
		updatecount=this.getSqlMapClientTemplate().update("userBattleHistory.updateUser", para);
		
		return updatecount>0 ?true:false;
	}

	public void insertBattleHistory(UserBattleHistory userBattle) {
		// TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert("userBattleHistory.insertUserBattle", userBattle);
	}

	public List<UserBattleHistory> rankByTimes(String gameType) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userBattleHistory.rankByTimes", gameType);
	}




}
