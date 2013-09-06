package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.BattleHistory;

public  class BattleHistoryDAOImpl extends SqlMapClientDaoSupport implements BattleHistoryDAO {

	public void insertBattleHistory(BattleHistory battleHistory) {
		// TODO Auto-generated method stub
		
		this.getSqlMapClientTemplate().insert("battleHistory.insertBattleHistory", battleHistory);
	}

	public List<BattleHistory> querAllHistory() {
		// TODO Auto-generated method stub
		List list=null;
		list=this.getSqlMapClientTemplate().queryForList("battleHistory.findAll");
		return list;
	}

	public List<BattleHistory> userRate(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		List list=null;
		list=this.getSqlMapClientTemplate().queryForList("battleHistory.findUserRate",ids);
		return list;
	}

	public int userRank(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findIsSpecial",ids);
	}

	public List<BattleHistory> winTotal(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		List list=null;
		list=this.getSqlMapClientTemplate().queryForList("battleHistory.findUserWin",ids);
		return list;
	}

	public int userTotalResult(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserTotalAll", ids);
	}

	public int userWinResult(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserWinTotalAll", ids);
	}

	public int userLoseResult(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserLoseTotalAll", ids);
	}

	public int userSpecialWin(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findIsSpecialWinNum",ids);
	}

	public int userSpecialLose(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findIsSpecialLoseNum",ids);
	}

	public int userSpeciaDraw(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findIsSpecialDrawNum",ids);
	}

	public int userDrawNum(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserDrawNumAll",ids);
	}

	public int userWinNum(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserWinNumAll",ids);
	}

	public int userLoseNum(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserLoseNumAll",ids);
	}

	public int userTotalResultWithDate(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserTotalWithDateAll", ids);
	}

	public int userWinResultWithDate(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserWinTotalWithDateAll", ids);
	}

	public int userLoseResultWithDate(Map<Object, Object> ids) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserLoseTotalWithDateAll", ids);
	}
	
	public int userDrawNumWithDate(Map<Object,Object> ids)
	{
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserDrawNumAllDate",ids);
	}
	
	public int userWinNumWithDate(Map<Object,Object> ids)
	{
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserWinNumAllDate",ids);
	}
	public int userLoseNumWithDate(Map<Object,Object> ids)
	{
		return (Integer) this.getSqlMapClientTemplate().queryForObject("battleHistory.findUserLoseNumAllDate",ids);
	}


}
