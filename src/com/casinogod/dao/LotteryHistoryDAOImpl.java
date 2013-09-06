package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.LotteryHistory;

public class LotteryHistoryDAOImpl extends SqlMapClientDaoSupport implements LotteryHistoryDAO {

	public List<LotteryHistory> querAll() {
		// TODO Auto-generated method stub
		List list=null;
	    list=this.getSqlMapClientTemplate().queryForList("lotteryHistory.findAll");
		return list;
	}

	public void insertLottery(LotteryHistory lottery) {
		// TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert("lotteryHistory.insertlottery", lottery);
	}

	public List<LotteryHistory> queryById(int id) {
		// TODO Auto-generated method stub
		List list=null;
	    list=this.getSqlMapClientTemplate().queryForList("lotteryHistory.findlotteryById",id);
		return list;
	}

	public boolean updateLottery(LotteryHistory lotteryHistory) {
		// TODO Auto-generated method stub
		int updateLottery;
		updateLottery=this.getSqlMapClientTemplate().update("lotteryHistory.updateLottery", lotteryHistory);
		return updateLottery>0?true:false;
	}

	public boolean deleteLottery(int lotteryId) {
		// TODO Auto-generated method stub
		int deleteLottery;
		deleteLottery=this.getSqlMapClientTemplate().delete("lotteryHistory.deleteLottery", lotteryId);
		return deleteLottery>0?true:false;
	}

}
