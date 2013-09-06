package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casinogod.dao.RequestDAOImpl;
import com.casinogod.pojo.FriendRequest;
import com.casinogod.utility.Utility;

public class RequestConfigService implements RequestConfig {
	
	private RequestDAOImpl requestDAO;
	
	private FriendRequest friendRequest;

	public List<FriendRequest> queryall() {
		// TODO Auto-generated method stub
		return requestDAO.querAll();
	}

	public boolean addFriendRequest(long owenId, long userId, String requestTime,int statue) {
		// TODO Auto-generated method stub
		friendRequest.setOwenId(owenId);
		friendRequest.setUserId(userId);
		friendRequest.setRequestDate(Utility.getNowString());
		friendRequest.setStatue(statue);
		requestDAO.insertItem(friendRequest);
		return true;
	}

	public List<FriendRequest> queryOwenId(long owenId) {
		// TODO Auto-generated method stub
		return requestDAO.queryByOwenId(owenId);
	}

	public List<FriendRequest> queryUserId(long userId) {
		// TODO Auto-generated method stub
		return requestDAO.queryByUserId(userId);
	}

	public List<String> queryStatue(FriendRequest friendRequest) {
		// TODO Auto-generated method stub
		return requestDAO.queryByStatue(friendRequest);
	}

	public void setRequestDAO(RequestDAOImpl requestDAO) {
		this.requestDAO = requestDAO;
	}

	public void setFriendRequest(FriendRequest friendRequest) {
		this.friendRequest = friendRequest;
	}

	public boolean updateStatue(FriendRequest friendRequest) {
		// TODO Auto-generated method stub
		return requestDAO.updateStatue(friendRequest);
	}

	public boolean deleteRequest(long owenId, long userId) {
		// TODO Auto-generated method stub
		
		Map <String,Long> map=new HashMap<String, Long>();
		map.put("owenId", owenId);
		map.put("userId", userId);
		return requestDAO.deleteRequest(map);
	}
	
	

}
