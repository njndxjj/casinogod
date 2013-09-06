package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.Product;

public class ProductDAOImpl extends SqlMapClientDaoSupport implements  ProductDAO {

	public List<Product> querAll() {
		// TODO Auto-generated method stub
		List<Product> list=null;
		list=this.getSqlMapClientTemplate().queryForList("product.findAllProduct");
		return list;
	}

	public void insertProduct(Product product) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("product.insertProduct", product);
	}

	public List<Product> queryById(String productId) {
		// TODO Auto-generated method stub
		List <Product> list=null;
		list=this.getSqlMapClientTemplate().queryForList("product.findProductById",productId);
		return list;
	}

	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		int updateProduct;
		updateProduct=this.getSqlMapClientTemplate().update("product.updateProduct", product);
		return updateProduct>0?true:false;
	}

	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		int updateProduct;
		updateProduct=this.getSqlMapClientTemplate().update("product.deleteProudct", id);
		return updateProduct>0?true:false;
	}


}
