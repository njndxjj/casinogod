package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.ChatRoom;

public interface ChatRoomDAO {
   
	public List <ChatRoom> querAllTime(String updateTime);
	
	public List <ChatRoom> querryAll();
	
	public void insertChatRoom(ChatRoom chatRoom);
	
	public List <ChatRoom> queryById(int id);

	public List <ChatRoom> queryByUserId(int userId);
	
	
}
