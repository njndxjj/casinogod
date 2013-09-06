package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.Lottery;


public interface LotteryConfig {
	
	public List<Lottery> queryall();
	
	public boolean addLottery(long userId,int lotteryId,int lotteryType, String lotteryValue,int betAmount,
			boolean result,int level,int num,String betDateTime);
	
	public List<Lottery> queryById(int id);
	
	public List<Lottery> queryByUserId(long userId);
	
	public List<Lottery> queryByUserIdAndId(long userId,int id);
	
	public List<Lottery> queryByUserIdAndLevel(int id,int level);
	
	public boolean updateResult(int level,boolean result,long userId,int lotteryId);
	
}
