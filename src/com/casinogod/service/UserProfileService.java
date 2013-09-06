package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.UserAccountDAOImpl;
import com.casinogod.dao.UserDAOImpl;
import com.casinogod.pojo.User;
import com.casinogod.pojo.UserAccount;
import com.casinogod.utility.Utility;

public class UserProfileService implements  UserProfile {
	
	
	private UserDAOImpl userDao;
	
	private UserAccountDAOImpl userAccountDAO;
	
	private User user;
	
	private UserAccount useraccount;
	
	public UserAccountDAOImpl getUserAccountDAO() {
		return userAccountDAO;
	}

	public void setUserAccountDAO(UserAccountDAOImpl userAccountDAO) {
		this.userAccountDAO = userAccountDAO;
	}

	public UserAccount getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(UserAccount useraccount) {
		this.useraccount = useraccount;
	}

	public UserDAOImpl getUserDao() {
		return userDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserAccount(UserAccount useraccount) {
		this.useraccount = useraccount;
	}

	public void setUserAccountDAOImpl(UserAccountDAOImpl userAccountDAO) {
		this.userAccountDAO = userAccountDAO;
	}

	public void setUserDao(UserDAOImpl userDao) {
		this.userDao = userDao;
	}

	public List<User> queryallUser() {
		// TODO Auto-generated method stub
	   List<User> list = userDao.querAllUser();
	   System.out.println("user Size:"+list.size());
	   return list;
	}

	
	public List <User> queryUserById(long id) {
		// TODO Auto-generated method stub
		List <User> list= userDao.quserUserId(id);
		return list;
	}

	
	public List<User> queryUserByName(String name) {
		// TODO Auto-generated method stub
		List <User> list=userDao.querUserName(name);
		return list;
	}

	
	public boolean addUser(String username, String password,String gender,String emailAddress,String telephone,String userType) {
		// TODO Auto-generated method stub
		
		user.setNickName(username);
		user.setGender(gender);
		user.setEmailAddress(emailAddress);
		user.setTelephone(telephone);    
        user.setRegisterDate(Utility.getNowString());
        user.setUserType(userType );
        user.setImage(null);
        user.setLevel(1);
        user.setExp(50);
        user.setVipLevel(0);
        user.setGold(0);
        user.setDiamond(0);
        user.setOtherNotes(null);
        user.setItems(null);  
        user.setFriendList(null);

        //add user info
        userDao.insertUser(user);
        
        //create userAccount;
        useraccount.setPassword(password);
        useraccount.setFreeze(0);
        useraccount.setUser(user);
        
        //add useraccount
        userAccountDAO.insertAccount(useraccount);
        
	    return true;
	}

	public boolean updateGold(User user) {
		
		return userDao.updateUserGold(user);
	}

	public boolean delete(long userId) {
		// TODO Auto-generated method stub
		          
        return userDao.deleteUser(userId);
	}

	public boolean updateExp(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUserExp(user);
	}

	public boolean updateFriend(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUserFriends(user);
	}

	public boolean addUserSNA(String username, String password, String gender,
			String emailAddress, String telephone,String userType,String snsId,String snsToken) {
		// TODO Auto-generated method stub
		user.setNickName(username);
		user.setGender(gender);
		user.setEmailAddress(emailAddress);
		user.setTelephone(telephone);    
        user.setRegisterDate(Utility.getNowString());
        user.setUserType(userType);
        user.setImage(null);
        user.setLevel(1);
        user.setExp(50);
        user.setVipLevel(0);
        user.setGold(0);
        user.setDiamond(0);
        user.setOtherNotes(null);
        user.setItems(null);  
        user.setFriendList(null);

        //add user info
        userDao.insertUser(user);
        
        //create userAccount;
        useraccount.setPassword(password);
        useraccount.setFreeze(0);
        useraccount.setUserType(userType);
        useraccount.setSnsId(snsId);
        useraccount.setSnsToken(snsToken);
        useraccount.setUser(user);
              
        //add useraccount
        userAccountDAO.insertAccount(useraccount);
        
	    return true;
	}

	public boolean updateProfile(long userId, String nickName, String gender,
			String emailAddress, String telephone, String image) {
		// TODO Auto-generated method stub
		List <User> list=userDao.quserUserId(userId);
		
		if(list.size()<0) return false;
		
		else
		{
			if(nickName==null) nickName=list.get(0).getNickName();
			if(gender==null) gender=list.get(0).getGender();
			if(emailAddress==null) emailAddress=list.get(0).getEmailAddress();
			if(telephone==null) telephone=list.get(0).getTelephone();
			if(image==null) image=list.get(0).getImage();
			
			return userDao.updateUserProfile(userId, nickName, gender, emailAddress, telephone, image);
		}
		
	}

}
