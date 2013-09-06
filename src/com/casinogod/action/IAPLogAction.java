package com.casinogod.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.IAPlog;
import com.casinogod.pojo.Item;
import com.casinogod.service.IAPLogService;
import com.casinogod.service.ItemConfigService;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IAPLogAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long userId;
	
	private String productId;
	
	private int quantity;
	
	private int money;
	
	private int statue;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private IAPLogService iAPLogService;

	
	

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setStatue(int statue) {
		this.statue = statue;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
	
	public void setiAPLogService(IAPLogService iAPLogService) {
		this.iAPLogService = iAPLogService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public String showIAPLog()
	{
		
		
		List <IAPlog> list=iAPLogService.queryall();
		
		boolean flag=false;
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("iapLogs", list);
		}
		
		
		return flag? SUCCESS:ERROR;
		
	}
	
	public void addIAPInfo()
	{
		
		
		iAPLogService.addIAPLog(userId, productId, quantity, money, statue, Utility.getNowString());	
		
		String responseJSON="sucess";
		
		try {
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	
}
