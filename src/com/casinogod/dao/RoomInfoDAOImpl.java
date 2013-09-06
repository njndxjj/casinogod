package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.RoomInfo;

public class RoomInfoDAOImpl extends SqlMapClientDaoSupport implements RoomInfoDAO {

	public List<RoomInfo> querAllHistory() {
		// TODO Auto-generated method stub
		
		List <RoomInfo> list=null;
		
		list=this.getSqlMapClientTemplate().queryForList("roomInfo.findAll");
		
		return list;
	}

	public void insertRoomInfo(RoomInfo roomInfo) {
		// TODO Auto-generated method stub
		
		this.getSqlMapClientTemplate().insert("roomInfo.insertRoomInfo", roomInfo);

	}

	public boolean updateRoomInfo(RoomInfo roomInfo) {
		// TODO Auto-generated method stub
		int updateRoom;
		updateRoom=this.getSqlMapClientTemplate().update("roomInfo.updateRoom", roomInfo);
		return updateRoom>0?true:false;
	}

	public boolean deleteRoomInfo(int id) {
		// TODO Auto-generated method stub
		int deleteRoom;
		deleteRoom=this.getSqlMapClientTemplate().delete("roomInfo.deleteRooom", id);
		return deleteRoom>0?true:false;
	}

}
