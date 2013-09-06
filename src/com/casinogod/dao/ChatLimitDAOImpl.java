package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.ChatLimit;

public class ChatLimitDAOImpl extends SqlMapClientDaoSupport implements ChatLimitDAO {

	public List<ChatLimit> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("chatLimit.findAll");
	}

	public void insertChatLimit(ChatLimit chatLimit) {
		// TODO Auto-generated method stub
		
		this.getSqlMapClientTemplate().insert("chatLimit.insertLimit", chatLimit);

	}

	public List<ChatLimit> queryById(int userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("chatLimit.findById",userId);
	}

	public boolean updateLimit(ChatLimit chatLimit) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("chatLimit.updateLimit", chatLimit);
		return updateCount>0?true:false;
	}
	public boolean deleteChatLimit(int userId) {
		// TODO Auto-generated method stub
		int deleteCount;
		deleteCount=this.getSqlMapClientTemplate().delete("chatLimit.deleteLimit", userId);
		return deleteCount>0?true:false;
	}

}
