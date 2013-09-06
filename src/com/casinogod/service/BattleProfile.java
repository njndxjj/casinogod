package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BattleHistory;


public interface BattleProfile {
	
	public List<BattleHistory> queryall();
	
	public boolean addBattleProfile(long battleId,long owenId, String userList,int battleStatue,
	
			int battleType,String gameType,String createTime,String result,String otherNotes,int level,int isSpecialWin);
	
	public boolean thressRate(long ownId,int battleStatue,int battleType,String createTime,int n,int gameType);
	
	public List <BattleHistory> winBattle(long ownId,int battleStatue,int battleType,String createTime,String endTime,int gameType);
	
	public int isSpecialWin(long ownId,int battleStatue,int battleType,String createTime,String endTime,int gameType);
	
	public int isSpecialWinNum(long ownId,int battleStatue,int battleType,String createTime,String endTime,int gameType);
	
	public int isSpecialLoseNum(long ownId,int battleStatue,int battleType,String createTime,String endTime,int gameType);
	
	public int isSpecialDrawNum(long ownId,int battleStatue,int battleType,String createTime,String endTime,int gameType);
	
	public int totalReuslt(long ownId,int battleStatue,int battleType);
	
	public int totalWinResult(long ownId,int battleStatue,int battleType);
	
	public int totalLoseResult(long ownId,int battleStatue,int battleType);
	
	public int totalReusltWithDate(long ownId,int battleStatue,int battleType,String createTime,String endTime);
	
	public int totalWinResultWithDate(long ownId,int battleStatue,int battleType,String createTime,String endTime);
	
	public int totalLoseResultWithDate(long ownId,int battleStatue,int battleType,String createTime,String endTime);
	
	public int totalDrawNum(long ownId,int battleStatue,int battleType);
	
	public int totalDrawNumWithDate(long ownId,int battleStatue,int battleType,String createTime,String endTime);
	
	public int totalWinNumt(long ownId,int battleStatue,int battleType);
	
	public int totalWinNumtWithDate(long ownId,int battleStatue,int battleType,String createTime,String endTime);
	
	public int totalLoseNum(long ownId,int battleStatue,int battleType);
	
	public int totalLoseNumWithDate(long ownId,int battleStatue,int battleType,String createTime,String endTime);
	


}
