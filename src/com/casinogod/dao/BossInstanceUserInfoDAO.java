package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import com.casinogod.pojo.BossInstanceUserInfo;
import com.casinogod.pojo.BossUserInfo;

public interface BossInstanceUserInfoDAO {
   
	public List <BossInstanceUserInfo> querAll();
	
	public void insertBossInstanceUserInfo(BossInstanceUserInfo bossInstanceUserInfo);
	
	
	public List <BossInstanceUserInfo> queryByUser(long userId);
	
	public List <BossInstanceUserInfo> queryByInstance(int bossInstance);
	
	public List <BossInstanceUserInfo> queryByInstanceAndId(Map<Object, Object> ids);
	
	
	public boolean updateReuslt(BossInstanceUserInfo bossInstanceUserInfo);
	
	
}
