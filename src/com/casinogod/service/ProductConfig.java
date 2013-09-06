package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.Product;


public interface ProductConfig {
	
	public List<Product> queryall();
	
	public boolean addProduct(String productId,String productName, int diamond,int unit);
	
	public List<Product> queryById(String productId);
	
	public boolean updateProduct(Product product);
	
	public boolean deleteProduct(int id);
	
}
