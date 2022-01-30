package com.dao;

import java.util.List;

import com.dto.Product;

public interface ProductDao {
	
	public List<Product> listProducts();
	public void addProduct(Product product);
	public void deleteProduct(Product product);
	public List<Product> searchProductsByCategory(String category);
	public List<Product> searchProductsByName(String name);
	public Product getProductsById(int productid);
	public void updateProductsById(Product product);

}
