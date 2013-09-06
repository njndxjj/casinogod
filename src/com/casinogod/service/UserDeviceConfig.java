package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.UserDevice;


public interface UserDeviceConfig {
	
	public List<String> queryall();
	
	public boolean addDevice(long userId,String deviceToken, String registerTime,String notes);
	
	public List<String> queryById(long userId);
	
	public List<UserDevice> queryallDevice();
	
	public boolean updateDevice(String deviceToken,long userId);
	
	public boolean deleteDevice(long userId);
	
}
