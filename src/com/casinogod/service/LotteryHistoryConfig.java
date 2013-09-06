package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.LotteryHistory;


public interface LotteryHistoryConfig {
	
	public List<LotteryHistory> queryall();
	
	public boolean addLottery(int lotteryType, String openDateTime,String result);
	
	public List<LotteryHistory> queryById(int id);
	
	public boolean updateLottery(LotteryHistory lotteryHistory);
	
	public boolean deleteLottery(int lotteryId);
	
	
}
