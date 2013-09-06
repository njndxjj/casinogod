package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.Product;

public interface ProductDAO {
   
	public List <Product> querAll();
	
	public void insertProduct(Product product);
	
	public List <Product> queryById(String productId);
	
	
	public boolean updateProduct(Product product);
	
	public boolean deleteProduct(int id);
	
}
