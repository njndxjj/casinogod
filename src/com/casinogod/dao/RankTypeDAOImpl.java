package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.RankType;

public class RankTypeDAOImpl extends SqlMapClientDaoSupport implements RankTypeDAO {

	public List<RankType> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("rankTypeInfor.findAll");
	}

	public void insertRankType(RankType rankType) {
		// TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert("rankTypeInfor.insertRankType", rankType);
	}

	public List<RankType> queryById(int typeId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("rankTypeInfor.findById", typeId);
	}

	public boolean updateRankType(RankType rankType) {
		// TODO Auto-generated method stub
		int updatecount;
		updatecount=this.getSqlMapClientTemplate().update("rankTypeInfor.updateRankType", rankType);
		return updatecount>0?true:false;
	}

	public boolean deleteRankType(int typeId) {
		// TODO Auto-generated method stub
		int deleteCount;
		deleteCount=this.getSqlMapClientTemplate().delete("rankTypeInfor.deleteRank", typeId);
		return false;
	}

}
