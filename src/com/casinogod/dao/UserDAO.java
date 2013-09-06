package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.User;

public interface UserDAO {
   
	public List <User> querUserName(String name);
	
	public List <User> quserUserId(long id);
	
	public List <User> querAllUser();
	
	public boolean updateUserGold(User user);
	
	public boolean updateUserExp(User user);
	
	public boolean updateUserFriends(User user);
	
	public boolean updateUserProfile(long userId,String nickName,String gender,String emailAddress,String telephone,String image);
	
	public void insertUser(User user);
	
	public boolean deleteUser(long id);
	

}
