package com.casinogod.dao;


import java.util.List;

import com.casinogod.pojo.RoomInfo;

public interface RoomInfoDAO {
   
	
	public List <RoomInfo> querAllHistory();
	
	public void insertRoomInfo(RoomInfo roomInfo);
	
	
	public boolean updateRoomInfo(RoomInfo roomInfo);
	
	
	public boolean deleteRoomInfo(int id);
	
	

}
