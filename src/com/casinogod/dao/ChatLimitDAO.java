package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.ChatLimit;

public interface ChatLimitDAO {
   
	public List <ChatLimit> querAll();
	
	public void insertChatLimit(ChatLimit chatLimit);
	
	public List <ChatLimit> queryById(int userId);

	
	public boolean updateLimit(ChatLimit chatLimit);
	
	public boolean deleteChatLimit(int userId);
	
}
