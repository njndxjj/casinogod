package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import com.casinogod.pojo.UserDevice;

public interface UserDeviceDAO {
   
	public List <String> querAll();
	
	public List <UserDevice> querAllDevice();
	
	public void insertItem(UserDevice userDevice);
	
	public List <String> queryById(long userId);
	
	public boolean updateDevice(Map userDevice);
	
	public boolean deleteDevice(long userId);
	
}
