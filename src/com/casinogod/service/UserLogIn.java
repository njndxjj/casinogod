package com.casinogod.service;



import java.util.List;

import com.casinogod.pojo.User;
import com.casinogod.pojo.UserAccount;

public interface UserLogIn {
	
	public  User  login(long userAccount, String password);
	
	public User   logInSNS(String userType,String snsId);
	
	public boolean isFreeze(long userAccount);
	
	public boolean updatePassword(long account,String password);
	
	public List <UserAccount> findAll();
	
	public UserAccount   getAccount(long userAccount);

}
