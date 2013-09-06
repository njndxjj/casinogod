package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.InviteTable;

public class InviteTableDAOImpl extends SqlMapClientDaoSupport implements InviteTableDAO {

	public List<InviteTable> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("inviteTable.findAll");
	}

	public void insertInviteTable(InviteTable inviteTable) {
		// TODO Auto-generated method stub
         this.getSqlMapClientTemplate().insert("inviteTable.insert", inviteTable);
	}

	public List<InviteTable> queryById(int userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("inviteTable.findAlluserId", userId);
	}

	public List<InviteTable> queryByInvitedId(int invitedId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("inviteTable.findByInvitedId", invitedId);
	}

}
