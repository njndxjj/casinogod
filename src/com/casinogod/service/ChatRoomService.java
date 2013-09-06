package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.ChatRoomDAOImpl;
import com.casinogod.pojo.ChatRoom;

public class ChatRoomService implements ChatRoomInterFace {
	
	private ChatRoom chatRoom;
	
	private ChatRoomDAOImpl chatRoomDAO;
	
	
	public void setChatRoom(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

	public void setChatRoomDAO(ChatRoomDAOImpl chatRoomDAO) {
		this.chatRoomDAO = chatRoomDAO;
	}

	public boolean addChatRoom(int userId, String content, String updateTime) {
		// TODO Auto-generated method stub
		
		chatRoom.setContent(content);
		chatRoom.setUserId(userId);
		chatRoom.setUpdateTime(updateTime);
		
		chatRoomDAO.insertChatRoom(chatRoom);
		
		return true;
	}

	public List<ChatRoom> queryUserById(int userId) {
		// TODO Auto-generated method stub
		return chatRoomDAO.queryByUserId(userId);
	}

	public List<ChatRoom> queryAllTime(String updateTime) {
		// TODO Auto-generated method stub
		return chatRoomDAO.querAllTime(updateTime);
	}
	
	public List<ChatRoom> queryAll() {
		// TODO Auto-generated method stub
		return chatRoomDAO.querryAll();
	}

	public List<ChatRoom> queryById(int id) {
		// TODO Auto-generated method stub
		return chatRoomDAO.queryById(id);
	}

}
