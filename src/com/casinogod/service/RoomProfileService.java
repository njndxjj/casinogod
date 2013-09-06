package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.RoomInfoDAOImpl;
import com.casinogod.pojo.RoomInfo;

public class RoomProfileService implements RoomProfile {
	
	private RoomInfo roomInfo;
	
	private RoomInfoDAOImpl roomInfoDAO;


	public RoomInfo getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(RoomInfo roomInfo) {
		this.roomInfo = roomInfo;
	}

	public RoomInfoDAOImpl getRoomInfoDAO() {
		return roomInfoDAO;
	}

	public void setRoomInfoDAO(RoomInfoDAOImpl roomInfoDAO) {
		this.roomInfoDAO = roomInfoDAO;
	}

	public List<RoomInfo> queryall() {
		// TODO Auto-generated method stub
		List list=null;
		list=roomInfoDAO.querAllHistory();
		return list;
	}

	public boolean addRoomProfile(int gameType, String roomLevel,
			int levelScore, int minHand, int maxHand, String beginTime,String endTime,String otherNotes) {
		// TODO Auto-generated method stub
		roomInfo.setGameType(gameType);
		roomInfo.setRoomLevel(roomLevel);
		roomInfo.setLevelScore(levelScore);
		roomInfo.setMinHand(minHand);
		roomInfo.setMaxHand(maxHand);
		roomInfo.setOtherNotes(otherNotes);
		roomInfo.setBeginTime(beginTime);
		roomInfo.setEndTime(endTime);
		
		
		roomInfoDAO.insertRoomInfo(roomInfo);
		
		return true;
	}

	public boolean update(RoomInfo room) {
		// TODO Auto-generated method stub
		
		return roomInfoDAO.updateRoomInfo(room);
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return roomInfoDAO.deleteRoomInfo(id);
	}

}
