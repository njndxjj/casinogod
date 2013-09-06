package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.Lottery;

public interface LotteryDAO {
   
	public List <Lottery> querAll();
	
	public void insertLottery(Lottery lottery);
	
	public List <Lottery> queryById(int id);
	
	
	public List <Lottery> queryByUserId(long userId);
	
	public List <Lottery> queryByUserIdAndLottery(long userId,int id);
	
	
	public List <Lottery> queryByUserIdAndLevel(int id,int level);
	
    public boolean updateResult(Lottery lottery);
	
}
