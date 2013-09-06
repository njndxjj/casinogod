package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BoardCastInfo;
import com.casinogod.pojo.Item;


public interface BoardCastConfig {
	
	public List<BoardCastInfo> queryall();
	
	public boolean addBoardCastInfo(String title,String content, String startDate,String endDate);
	
	public List<BoardCastInfo> queryBybetwend(String startDate,String endDate);
	
	
	
}
