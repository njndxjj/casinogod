package com.casinogod.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Item;
import com.casinogod.pojo.ItemUser;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.ItemUserConfigService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigItem extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int gameType;
	
	private String itemName;
	
	private int itemPrice;
	
	private String comment;
	
	private int id;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private ItemConfigService itemConfigService;
	
	private ItemUserConfigService itemUserConfigService;

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

	public void setItemConfigService(ItemConfigService itemConfigService) {
		this.itemConfigService = itemConfigService;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	

	public void setItemUserConfigService(ItemUserConfigService itemUserConfigService) {
		this.itemUserConfigService = itemUserConfigService;
	}

	public String queryAll()
	{
		boolean flag=false;
		
		List <Item> list=itemConfigService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("itemInfos", list);
			Map<String, Object> attibutes = ActionContext.getContext().getSession();
			long account=Long.valueOf((String)attibutes.get("account"));
			if(account==100)
				return "adminPage";
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addItemInfo()
	{
		boolean flag=false;
		
		flag=itemConfigService.addItem(this.gameType, this.itemName, this.itemPrice, this.comment);
	
		return flag?SUCCESS:ERROR;
		
	}
	
	public String findItemById() 
	{
		boolean flag=false;
		
		List <Item> list=itemConfigService.queryById(id);
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("id", list.get(0).getId());
			resquest.setAttribute("name", list.get(0).getItemName());				
			resquest.setAttribute("gameType", list.get(0).getGameType());
			resquest.setAttribute("itemprice", list.get(0).getItemPrice());
		}
		
		return flag? SUCCESS:ERROR;
	}
	
	public String updateItem() 
	{
		boolean flag=false;
		
		Item item=new Item();
		
		item.setId(this.id);
		item.setItemName(this.itemName);
		item.setGameType(this.gameType);
		item.setItemPrice(this.itemPrice);
		item.setComment(this.comment);
		
		flag=itemConfigService.updateItem(item);
		
		item=itemConfigService.queryById(this.id).get(0);
		
		if(flag)
		{
			List <ItemUser> list=itemUserConfigService.getUserItem(this.id);
			
			//update itemUser name
			if(list.size()>0)
			{
				itemUserConfigService.updateItemUserName(item.getItemName(), item.getId());
			}
		}
		
		return flag? SUCCESS:ERROR;
	}
	
	
	public String deleteItemById() 
	{
		boolean flag=false;
		
		flag=itemConfigService.deleteItem(this.id);
		
		return flag? SUCCESS:ERROR;
	}
}
