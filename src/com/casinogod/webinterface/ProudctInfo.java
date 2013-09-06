package com.casinogod.webinterface;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Item;
import com.casinogod.pojo.Product;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.ProductService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ProudctInfo extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private ProductService  productService;

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void queryAll()
	{

		List <Product> list=productService.queryall();
		
		String responseJSON= "";    
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       HashMap< String, Object> map = new HashMap<String, Object>();
	    	
	       map.put("product", list);
	       
	       responseJSON +=JSONObject.fromObject(map).toString();
	    	
		   response.setStatus(200);	
	    
	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find any product Infromation");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.product_Information);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
		}
			
		try {
			responseJSON=CustomBase64.encode(responseJSON);		
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
