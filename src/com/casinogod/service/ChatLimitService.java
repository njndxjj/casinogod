package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.ChatLimitDAOImpl;
import com.casinogod.pojo.ChatLimit;

public class ChatLimitService implements ChatLimitInterFace {
	
	private ChatLimit chatLimit;
	private ChatLimitDAOImpl chatLimitDAO;
	
	public void setChatLimit(ChatLimit chatLimit) {
		this.chatLimit = chatLimit;
	}

	public void setChatLimitDAO(ChatLimitDAOImpl chatLimitDAO) {
		this.chatLimitDAO = chatLimitDAO;
	}

	public List<ChatLimit> queryAll() {
		// TODO Auto-generated method stub
		return chatLimitDAO.querAll();
	}

	public boolean addChatLimit(int userId, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		chatLimit.setEndTime(endTime);
		chatLimit.setStartTime(startTime);
		chatLimit.setUserId(userId);
		chatLimitDAO.insertChatLimit(chatLimit);
		return true;
	}

	public boolean update(String startTime, String endTime, int userId) {
		// TODO Auto-generated method stub
		chatLimit.setEndTime(endTime);
		chatLimit.setStartTime(startTime);
		chatLimit.setUserId(userId);
		return chatLimitDAO.updateLimit(chatLimit);
	}

	public boolean delete(int userId) {
		// TODO Auto-generated method stub
		return chatLimitDAO.deleteChatLimit(userId);
	}

	public List<ChatLimit> queryUserById(int userId) {
		// TODO Auto-generated method stub
		return chatLimitDAO.queryById(userId);
	}
}

	
