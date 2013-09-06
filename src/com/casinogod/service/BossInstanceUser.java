package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BossInstanceUserInfo;
import com.casinogod.pojo.BossUserInfo;


public interface BossInstanceUser {
	
	public List<BossInstanceUserInfo> queryall();
	
	public boolean addBossInstanceUserInfo(int bossInstance,long userId,int result);
	
	public List<BossInstanceUserInfo> queryByUserId(long userId);
	
	public List<BossInstanceUserInfo> queryByInstance(int bossInstance);
	
	
	public List<BossInstanceUserInfo> queryByInstanceAndUser(long userId,int bossInstance);
	
	public boolean updateResult(BossInstanceUserInfo bossInstanceUserInfo);
	
}
