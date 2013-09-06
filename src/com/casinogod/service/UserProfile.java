package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.User;

public interface UserProfile {
	
	public List<User> queryallUser();
	
	public boolean addUser(String username, String password,String gender,String emailAddress,String telephone,String userType );
	
	public boolean addUserSNA(String username, String password,String gender,String emailAddress,String telephone,String userType,String snsId,String snsToken);
	
	public boolean updateGold(User user);
	
	public boolean updateExp(User user);
	
	public boolean updateFriend(User user);
	
	public boolean updateProfile(long userId,String nickName,String gender,String emailAddress,String telephone,String image);

	public boolean delete(long userId);
	
	public List <User> queryUserById(long id);
	
	public List <User> queryUserByName(String name);
	

}
