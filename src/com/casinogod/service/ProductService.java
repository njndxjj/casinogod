package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.ProductDAO;
import com.casinogod.dao.ProductDAOImpl;
import com.casinogod.pojo.Product;

public class ProductService implements ProductConfig {
	
	private ProductDAOImpl productDAO;
	private Product product;
	

	public void setProductDAO(ProductDAOImpl productDAO) {
		this.productDAO = productDAO;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> queryall() {
		// TODO Auto-generated method stub
		return productDAO.querAll();
	}

	public boolean addProduct(String productId, String productName,
			int diamond, int unit) {
		// TODO Auto-generated method stub
		boolean flag=false;
		product.setProductId(productId);
		product.setProductName(productName);
		product.setDiamond(diamond);
		product.setUnit(unit);
		productDAO.insertProduct(product);
		flag=true;
		return flag;
	}

	public List<Product> queryById(String productId) {
		// TODO Auto-generated method stub
		return productDAO.queryById(productId);
	}

	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDAO.updateProduct(product);
	}

	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		return productDAO.deleteProduct(id);
	}

}
