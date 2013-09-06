package com.casinogod.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.Lottery;

public class LotteryDAOImpl extends SqlMapClientDaoSupport implements LotteryDAO {

	public List<Lottery> querAll() {
		// TODO Auto-generated method stub
		List <Lottery> list=null;
		list=this.getSqlMapClientTemplate().queryForList("lottery.findAll");
		return list;
	}

	public void insertLottery(Lottery lottery) {
		// TODO Auto-generated method stub
       this.getSqlMapClientTemplate().insert("lottery.insertlottery", lottery);
	}

	public List<Lottery> queryById(int id) {
		// TODO Auto-generated method stub
		List <Lottery> list=null;
		list=this.getSqlMapClientTemplate().queryForList("lottery.findlotteryById",id);
		return list;
	}

	public List<Lottery> queryByUserId(long userId) {
		// TODO Auto-generated method stub
		List <Lottery> list=null;
		list=this.getSqlMapClientTemplate().queryForList("lottery.findlotteryByUserId",userId);
		return list;
	}

	public List<Lottery> queryByUserIdAndLottery(long userId, int id) {
		// TODO Auto-generated method stub
		Map <String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("lotteryId", id);
		List <Lottery> list=null;
		list=this.getSqlMapClientTemplate().queryForList("lottery.findlotteryByUserIdAndLottery",map);
		return list;
	}

	public List<Lottery> queryByUserIdAndLevel(int id, int level) {
		// TODO Auto-generated method stub
		Map <String,Object> map=new HashMap<String, Object>();
		map.put("level", level);
		map.put("lotteryId", id);
		List <Lottery> list=null;
		list=this.getSqlMapClientTemplate().queryForList("lottery.findlotteryByIdAndLevel",map);
		return list;
	}

	public boolean updateResult(Lottery lottery) {
		// TODO Auto-generated method stub
		int updateCount=0;
		updateCount=this.getSqlMapClientTemplate().update("lottery.updateResult", lottery);
		return updateCount>0?true:false;
	}

}
