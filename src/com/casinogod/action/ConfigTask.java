package com.casinogod.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Item;
import com.casinogod.pojo.Task;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.TaskService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jmx.snmp.tasks.TaskServer;

public class ConfigTask extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int taskId;
	
	private int taskType;
	
	private int taskRate;
	
	private int itemId;
	
	private int itemNum;
	
	private int isDaily;
	
	private int battleType;
	
	private int gameType;
	
	private String description;
		
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private TaskService taskService;

	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
	
	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public void setTaskRate(int taskRate) {
		this.taskRate = taskRate;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void setIsDaily(int isDaily) {
		this.isDaily = isDaily;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	

	public void setBattleType(int battleType) {
		this.battleType = battleType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public String queryAll()
	{
		boolean flag=false;
		
		List <Task> list=taskService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("taskInfos", list);
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addTask()
	{
		boolean flag=false;
		
		flag=taskService.addTask(this.taskType, this.taskRate, this.itemId, this.itemNum,this.isDaily,this.battleType,this.gameType,
				this.description);
	
		return flag?SUCCESS:ERROR;
		
	}
	
	public String findTaskByType() 
	{
		boolean flag=false;
		
		List <Task> list=taskService.queryByTypeAndRate(this.taskType, this.taskRate);
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("taskId", list.get(0).getTaskId());
			resquest.setAttribute("taskType", list.get(0).getTaskType());
			resquest.setAttribute("taskRate", list.get(0).getTaskRate());				
			resquest.setAttribute("itemId", list.get(0).getItemId());
			resquest.setAttribute("itemNum", list.get(0).getItemNum());
			resquest.setAttribute("isDaily", list.get(0).getIsDaily());
			resquest.setAttribute("battleType", list.get(0).getBattleType());
			resquest.setAttribute("gameType", list.get(0).getGameType());
			resquest.setAttribute("description", list.get(0).getDescription());
		}
		
		return flag? SUCCESS:ERROR;
	}
	
	public String updateTask() 
	{
		boolean flag=false;
		
		Task task=new Task();
	    task.setItemId(this.itemId);
	    task.setItemNum(this.itemNum);
		task.setTaskRate(this.taskRate);
		task.setTaskType(this.taskType);
		task.setIsDaily(this.isDaily);
		task.setGameType(this.gameType);
		task.setBattleType(this.battleType);
		task.setDescription(description);
		
		flag=taskService.updateTask(task);
		
		return flag? SUCCESS:ERROR; 
	}

	public String delete() 
	{
		boolean flag=false;
		
		flag=taskService.deleteTask(this.taskType, this.taskRate);
		
		return flag? SUCCESS:ERROR;
	}
}
