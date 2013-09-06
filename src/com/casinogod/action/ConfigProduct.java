package com.casinogod.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Product;
import com.casinogod.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigProduct extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String productId;
	
	private String productName;
	
	private String diamond;
	
	private int unit;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private ProductService productService;
		
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setDiamond(String diamond) {
		this.diamond = diamond;
	}

	public void setUnit(int unit) {
		this.unit = unit;
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
	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String addProduct()
	{
		boolean flag=false;
		
		flag=productService.addProduct(this.productId,this.productName,Integer.valueOf(this.diamond),this.unit);
		
		return flag?SUCCESS:ERROR;
		
	}
	
	public String querryProduct()
	{
		boolean flag=false;
		
		List<Product> list=productService.queryall();
		
		
		if(list.size()>0)
			flag=true;
		
		resquest.setAttribute("products", list);
		
		return flag?SUCCESS:ERROR;
		
	}
	
	public String updateProduct()
	{
		boolean flag=false;
		
		Product product=new Product();
		
		product.setId(this.id);
		
		product.setProductId(this.productId);
		
		product.setProductName(this.productName);
		
		product.setDiamond(Integer.valueOf(this.diamond));
		
		product.setUnit(this.unit);
		
		flag=productService.updateProduct(product);
		
		return flag?SUCCESS:ERROR;
		
	}
	
	String deleteProduct(int id)
	{
		boolean flag=false;
		
		flag=productService.deleteProduct(id);
		
		return flag?SUCCESS:ERROR;
		
	}
	

}
