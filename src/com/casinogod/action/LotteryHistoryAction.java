package com.casinogod.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.LotteryHistory;
import com.casinogod.service.LotteryHistoryService;
import com.opensymphony.xwork2.ActionSupport;

public class LotteryHistoryAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int lotteryId;
	
	private int lotteryType;
	
	private String result;
	
	private String openDateTime;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private LotteryHistoryService lotteryHistoryService;
	
	private static Logger log = Logger.getLogger(LotteryHistoryAction.class);

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	

	public void setLotteryType(int lotteryType) {
		this.lotteryType = lotteryType;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setOpenDateTime(String openDateTime) {
		this.openDateTime = openDateTime;
	}

	public void setLotteryHistoryService(LotteryHistoryService lotteryHistoryService) {
		this.lotteryHistoryService = lotteryHistoryService;
	}
	
	public void setLotteryId(int lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String queryAll()
	{
		boolean flag=false;
		
		List <LotteryHistory> list=lotteryHistoryService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("lotteryInfos", list);
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addLotteryInfo()
	{
		boolean flag=false;
		
		System.out.println("result:-->"+this.result);
		
		flag=lotteryHistoryService.addLottery(this.lotteryType,this.openDateTime,this.result);
		
		if(flag)
			return queryAll();
		
		return ERROR;
		
	}
	
	public String queryById()
	{
		boolean flag=false;
		
		List <LotteryHistory> list=lotteryHistoryService.queryById(this.lotteryId);
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("lotteryInfos", list);
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	
	public String updateLottery()
	{
		boolean flag=false;
		
		LotteryHistory lotteryHistory=new LotteryHistory();
		
		lotteryHistory.setLotteryId(this.lotteryId);
		lotteryHistory.setLotteryType(this.lotteryType);
		
		String startTime=this.openDateTime;
		startTime=startTime.replace(String.valueOf(startTime.charAt(10)), "\t");
		
		lotteryHistory.setOpenDateTime(startTime);
		
//		log.info("result -->"+(this.result).replace("+", "\t"));
		
		lotteryHistory.setResult(this.result);
		
		flag=lotteryHistoryService.updateLottery(lotteryHistory);
		
		return flag?SUCCESS:ERROR;
	}
	
	public String deleteLottery()
	{
		boolean flag=false;
		
		flag=lotteryHistoryService.deleteLottery(this.lotteryId);
		
		return flag?SUCCESS:ERROR;
	}
	
	
	

}
