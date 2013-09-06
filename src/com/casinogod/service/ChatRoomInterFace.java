package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.ChatRoom;


public interface ChatRoomInterFace {
		
	public boolean addChatRoom(int userId, String content,String updateTime);
	
	public List <ChatRoom> queryUserById(int userId);
	
	public List <ChatRoom> queryAllTime(String updateTime);
	
	public List <ChatRoom> queryAll();
	
	public List <ChatRoom> queryById(int id);

	

}
