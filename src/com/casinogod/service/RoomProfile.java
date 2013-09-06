package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.RoomInfo;


public interface RoomProfile {
	
	public List<RoomInfo> queryall();
	
	public boolean addRoomProfile(int gameType,String roomlevel, int levelscore,int minhand,
			int maxhand,String beginTime,String endTime,String otherNotes);
	
	public boolean update(RoomInfo room);

	public boolean delete(int id);

}
