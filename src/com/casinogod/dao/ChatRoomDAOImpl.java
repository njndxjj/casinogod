package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.ChatRoom;

public class ChatRoomDAOImpl extends SqlMapClientDaoSupport implements ChatRoomDAO {

	public List<ChatRoom> querAllTime(String updateTime) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("chatRoom.findAllByTime", updateTime);
	}

	public void insertChatRoom(ChatRoom chatRoom) {
		// TODO Auto-generated method stub
		
		this.getSqlMapClientTemplate().insert("chatRoom.insertChat", chatRoom);


	}

	public List<ChatRoom> queryById(int id) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("chatRoom.findById",id);
	}

	public List<ChatRoom> queryByUserId(int userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("chatRoom.findByUserId",userId);
	}

	public List<ChatRoom> querryAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("chatRoom.findAll");
	}

}
