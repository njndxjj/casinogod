package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.User;
import com.casinogod.pojo.UserAccount;

public interface UserAccountDAO {
   

	public List<UserAccount> quserUserId(long account);
	
	public List<UserAccount> quserUserSNS(String userType,String snsId);
	
	public List <UserAccount> querAllUser();
	
	public void insertAccount(UserAccount userAccount);
	
	public boolean updatePassword(UserAccount userAccount);

}
