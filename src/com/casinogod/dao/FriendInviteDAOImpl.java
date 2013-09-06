package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.FriendInvite;

public class FriendInviteDAOImpl extends SqlMapClientDaoSupport implements FriendInviteDAO {

	public List<FriendInvite> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("friendInvite.findAll");
	}

	public void insertFriendInvite(FriendInvite friendInvite) {
		// TODO Auto-generated method stub
          this.getSqlMapClientTemplate().insert("friendInvite.insertCode", friendInvite);
	}

	public List<FriendInvite> queryById(int userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("friendInvite.findById", userId);
	}

	public boolean updateCount(FriendInvite friendInvite) {
		// TODO Auto-generated method stub
		int countUpdate;
		countUpdate=this.getSqlMapClientTemplate().update("friendInvite.updateCount", friendInvite);
		return countUpdate>0?true:false;
	}

	public List<FriendInvite> queryByCode(String inviteCode) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("friendInvite.findByCode", inviteCode);
	}

}
