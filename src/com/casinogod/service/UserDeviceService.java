package com.casinogod.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.casinogod.dao.UserDeviceDAOImpl;
import com.casinogod.pojo.UserDevice;

public class UserDeviceService implements UserDeviceConfig {
	
	private UserDevice userDevice;
	
	private UserDeviceDAOImpl userDeviceDAO;
	
	public void setUserDevice(UserDevice userDevice) {
		this.userDevice = userDevice;
	}

	public void setUserDeviceDAO(UserDeviceDAOImpl userDeviceDAO) {
		this.userDeviceDAO = userDeviceDAO;
	}

	public List<String> queryall() {
		// TODO Auto-generated method stub
		return userDeviceDAO.querAll();
	}

	public boolean addDevice(long userId, String deviceToken,
			String registerTime, String notes) {
		// TODO Auto-generated method stub
		userDevice.setUserId(userId);
		userDevice.setDeviceToken(deviceToken);
		userDevice.setRegisterTime(registerTime);
		userDevice.setNotes(notes);
		userDeviceDAO.insertItem(userDevice);
		return true;
		
	}

	public List<String> queryById(long userId) {
		// TODO Auto-generated method stub
		return userDeviceDAO.queryById(userId);
	}

	public List<UserDevice> queryallDevice() {
		// TODO Auto-generated method stub
		return userDeviceDAO.querAllDevice();
	}

	public boolean updateDevice(String deviceToken, long userId) {
		// TODO Auto-generated method stub
		Map <Object,Object> device=new HashedMap();
		device.put("deviceToken", deviceToken);
		device.put("userId", userId);
		return userDeviceDAO.updateDevice(device);
	}

	public boolean deleteDevice(long userId) {
		// TODO Auto-generated method stub
		return userDeviceDAO.deleteDevice(userId);
	}

}
