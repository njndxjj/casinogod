package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.BoardCastInfo;;

public interface BoardCastDAO {
   
	public List <BoardCastInfo> querAll();
	
	public void insertItem(BoardCastInfo boardCastInfo);
	
	public List <BoardCastInfo> queryByDate(BoardCastInfo boardCastInfo);
	
	public void deleteById(int id);
	
}
