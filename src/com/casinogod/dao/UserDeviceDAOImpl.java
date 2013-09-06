package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.UserDevice;

public class UserDeviceDAOImpl extends SqlMapClientDaoSupport implements UserDeviceDAO {

	public List<String> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userDeviceInfor.findAll");
	}

	public void insertItem(UserDevice userDevice) {
		// TODO Auto-generated method stub
		
		this.getSqlMapClientTemplate().insert("userDeviceInfor.insertDevice", userDevice);

	}

	public List<String> queryById(long userId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userDeviceInfor.findByUser",userId);
	}

	public List<UserDevice> querAllDevice() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userDeviceInfor.findAllDevice");
	}

	public boolean updateDevice(Map userDevice) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("userDeviceInfor.updateDevice", userDevice);
		return updateCount>0?true:false;
	}

	public boolean deleteDevice(long userId) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("userDeviceInfor.deleteDevice", userId);
		return updateCount>0?true:false;
	}

}
