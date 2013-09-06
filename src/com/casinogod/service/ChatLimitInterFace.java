package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.ChatLimit;


public interface ChatLimitInterFace {
	
	public List<ChatLimit> queryAll();
	
	public boolean addChatLimit(int userId, String startTime,String endTime);
	
	
	public boolean update(String startTime, String endTime,int userId);

	public boolean delete(int userId);
	
	public List <ChatLimit> queryUserById(int userId);

	

}
