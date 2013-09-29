package com.casinogod.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.RankUserInfo;

public class UserRankDAOImpl extends SqlMapClientDaoSupport implements UserRankDAO {

	public void insertUserRank(RankUserInfo rankUserInfo) {
		// TODO Auto-generated method stub
		
		this.getSqlMapClientTemplate().insert("rankUserInfor.insertRankUser", rankUserInfo);

	}

	public List<RankUserInfo> queryByType(int typeId,String column) {
		// TODO Auto-generated method stub
		Map <Object,Object > ids=new HashMap<Object, Object>();
		ids.put("typeId", typeId);
		ids.put("column", column);
		return this.getSqlMapClientTemplate().queryForList("rankUserInfor.findType", ids);
	}

	public boolean updateRank(RankUserInfo rankUserInfo) {
		// TODO Auto-generated method stub
		int updateCount;
	    updateCount=this.getSqlMapClientTemplate().update("rankUserInfor.updateRankUser", rankUserInfo);
		return updateCount>0?true:false;
	}

	public List<RankUserInfo> queryByTypeUser(int typeId, int userId) {
		// TODO Auto-generated method stub
		Map <Object,Object > ids=new HashMap<Object, Object>();
		ids.put("typeId", typeId);
		ids.put("userId", userId);
		return this.getSqlMapClientTemplate().queryForList("rankUserInfor.findTypeandUser",ids);
	}

}
