package com.casinogod.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Item;
import com.casinogod.pojo.ItemUser;
import com.casinogod.pojo.User;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.ItemHistoryConfigService;
import com.casinogod.service.ItemUserConfigService;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class ItemPurchase extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	
	private int itemId;
	
	private long userId;
	
	private long giftUserId;
	
	private String itemName;
	
	private int itemNum;
	
	private int gameType;
	
	private String comment;
	
	private int gamePrice;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private  UserProfileService   userProfileService;
	
	private  ItemConfigService    itemConfigService;
	
	private  ItemHistoryConfigService   itemHistoryConfigService;
	
	private  ItemUserConfigService itemUserConfigService;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setGiftUserId(long giftUserId) {
		this.giftUserId = giftUserId;
	}

	public void setGamePrice(int gamePrice) {
		this.gamePrice = gamePrice;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.resquest=request;
	}
	
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public void setItemConfigService(ItemConfigService itemConfigService) {
		this.itemConfigService = itemConfigService;
	}

	public void setItemHistoryConfigService(
			ItemHistoryConfigService itemHistoryConfigService) {
		this.itemHistoryConfigService = itemHistoryConfigService;
	}
	
	
	public void setItemUserConfigService(ItemUserConfigService itemUserConfigService) {
		this.itemUserConfigService = itemUserConfigService;
	}

	public String buyitems()
	
	{
List <User> list=userProfileService.queryUserById(this.userId);
		
		if(list.size()>0) 
		{
			User user=list.get(0);
			
			List <Item> items=itemConfigService.queryById(Integer.valueOf(this.itemId));
			
			Item item=items.get(0);
			
			if(item.getItemName().equals("Gold"))
			{
				
				//buy gold
				
				if(user.getDiamond()-(this.gamePrice*this.itemNum)>0&&user.getDiamond()>0)
			
				{
					int diamond=user.getDiamond()-(item.getItemPrice()*this.itemNum);
					int gold=user.getGold()+this.itemNum;
					user.setGold(gold);
					user.setDiamond(diamond);
				  
					//update userProfile
					userProfileService.updateGold(user);
				    
				    String purchaseTime=Utility.getNowString();
				   
				    //add itemHistory
				    itemHistoryConfigService.addItemUser(this.gameType,this.userId,0,this.itemName,this.itemNum,
				    0, purchaseTime, this.comment);
				    
				    List <User> listUser=userProfileService.queryUserById(this.userId);
				    
				    resquest.setAttribute("useItems", user.getItems());
				    return SUCCESS;
					
				}
			
				else
					return "buyFail";
			}
			
			else
			
			{
				if(user.getDiamond()-(this.gamePrice*this.itemNum)>0&&user.getDiamond()>0)
				
				{
					int diamond=user.getDiamond()-(item.getItemPrice()*this.itemNum);
					user.setGold(user.getGold());
					user.setDiamond(diamond);
			  
					//update userProfile
					userProfileService.updateGold(user);
			    
					String purchaseTime=Utility.getNowString();
			   
					//add or update itemUser;
					List <ItemUser> itemUsers=itemUserConfigService.getItem(this.userId,this.itemId,this.gameType);
			  
					if(itemUsers.size()>0)
					{
						//update itemUser
						ItemUser itemUser=itemUsers.get(0);
						itemUser.setItemNum(itemUser.getItemNum()+this.itemNum);
						itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
			    		itemUser.getItemNum());
			    	
					}else
					{
						itemUserConfigService.addItemUser(this.itemId,this.userId,this.itemName, this.itemNum,
			    		this.gameType,this.comment);
					}
					//add itemHistory
					itemHistoryConfigService.addItemUser(this.gameType,this.userId,0,this.itemName,this.itemNum,
							0, purchaseTime, this.comment);
			    
					List <User> listUser=userProfileService.queryUserById(this.userId);
			    
					resquest.setAttribute("useItems", user.getItems());
			    
					return SUCCESS;
				
				}
			
				else
					return "buyFail";
			}
		}
		
		return ERROR;
    }

	public String giftItems()
	{
	    boolean flag=false;
	    
	    List <User> list=userProfileService.queryUserById(this.userId);
	    
	    List <User> giftList=userProfileService.queryUserById(this.giftUserId);
		
		if(list.size()>0&&giftList.size()>0) 
		
		{
			User user=list.get(0);

			List <ItemUser> itemUsers=user.getItems();
			
			ItemUser itemUserTemp=new ItemUser();
			
			for(ItemUser itemUser :itemUsers)
			{
				if(itemUser.getItemId()==this.itemId)
				{
					itemUserTemp=itemUser;
					 break;
				}
			}
			
			if(itemUserTemp.getItemNum()-this.itemNum>0)
			{
			
			  //update itemUser;
			  List <ItemUser> itemUsersBuy=itemUserConfigService.getItem(this.userId,this.itemId,this.gameType);
			  
			  //update itemUser
			  ItemUser itemUser=itemUsersBuy.get(0);
			 
			  itemUser.setItemNum(itemUser.getItemNum()-this.itemNum);
			  
			  itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
			    			itemUser.getItemNum());
			  
			   //add itemHistory
			  itemHistoryConfigService.addItemUser(this.gameType,this.userId,2,this.itemName,this.itemNum,
			    this.giftUserId, Utility.getNowString(), this.comment);
			
			}
			else if(itemUserTemp.getItemNum()-this.itemNum==0)
			{
				
				// update itemUser;
				List <ItemUser> itemUsersBuy=itemUserConfigService.getItem(this.userId,this.itemId,this.gameType);
				  
				//update itemUser
				ItemUser itemUser=itemUsersBuy.get(0);
				
				itemUserConfigService.deleteItemUser(itemUser.getId());			
				  //add itemHistory
				 itemHistoryConfigService.addItemUser(this.gameType,this.userId,2,this.itemName,this.itemNum,
				this.giftUserId, Utility.getNowString(), this.comment);
				
			}
			else
			{
				flag=false;
			}
			
      //update or add gift item
			
	 List <ItemUser> itemUsersGift=itemUserConfigService.getItem(this.giftUserId,this.itemId,this.gameType);		
	 
	 if(itemUsersGift.size()>0)
	 {
		//update itemUser
		ItemUser itemUser=itemUsersGift.get(0);
		 
	    itemUser.setItemNum(itemUser.getItemNum()+this.itemNum);
		  
	    itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
		    			itemUser.getItemNum()); 
	 }
	 else
	 {
		itemUserConfigService.addItemUser(this.itemId,this.giftUserId,this.itemName, this.itemNum,
		    		this.gameType,this.comment);
	 }
			
   
	 List <User> listUser=userProfileService.queryUserById(this.userId);
			 
     resquest.setAttribute("useItems", listUser.get(0).getItems());
			 
	 flag=true;
	 
	 return flag?SUCCESS:ERROR;
	}	
		return "UserError";	
	}	
		
	public String purchase()
	
	{
		 boolean flag=false;
		    
		    List <User> list=userProfileService.queryUserById(this.userId);
			
			if(list.size()>0) 
			
			{
				User user=list.get(0);

				List <ItemUser> itemUsers=user.getItems();
				
				ItemUser itemUserTemp=new ItemUser();
				

				for(ItemUser itemUser :itemUsers)
				{
					if(itemUser.getItemId()==this.itemId)
					{
						itemUserTemp=itemUser;
						 break;
					}
				}
				
				if(itemUserTemp.getItemNum()-this.itemNum>0)
				{
				
				  //update itemUser;
				  List <ItemUser> itemUsersBuy=itemUserConfigService.getItem(this.userId,this.itemId,this.gameType);
				  
				  //update itemUser
				  ItemUser itemUser=itemUsersBuy.get(0);
				 
				  itemUser.setItemNum(itemUser.getItemNum()-this.itemNum);
				  
				  itemUserConfigService.updateItemUser(itemUser.getUserId(), itemUser.getItemName(),itemUser.getGameType(),
				    			itemUser.getItemNum());
				  //add itemHistory
				 itemHistoryConfigService.addItemUser(this.gameType,this.userId,1,this.itemName,this.itemNum,
				    0, Utility.getNowString(), this.comment);
				 
				 
				 
				 List <User> listUser=userProfileService.queryUserById(this.userId);
				 
				 resquest.setAttribute("useItems", listUser.get(0).getItems());
				 
				 flag=true;
				
				}
				else if(itemUserTemp.getItemNum()-this.itemNum==0)
				{
					
					// update itemUser;
					List <ItemUser> itemUsersBuy=itemUserConfigService.getItem(this.userId,this.itemId,this.gameType);
					  
					//update itemUser
					ItemUser itemUser=itemUsersBuy.get(0);
					
					itemUserConfigService.deleteItemUser(itemUser.getId());			
					  //add itemHistory
					 itemHistoryConfigService.addItemUser(this.gameType,this.userId,1,this.itemName,this.itemNum,
					 0, Utility.getNowString(), this.comment);
					 
					 List <User> listUser=userProfileService.queryUserById(this.userId);
					 
					 resquest.setAttribute("useItems", listUser.get(0).getItems());
					 
					 flag=true;
				}
				else
				{
					flag=false;
				}
				
	        return flag?SUCCESS:ERROR;
		}	
			return "UserError";	
	}
	
}
