package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dto.Product;

@Repository("ProductDaoImp")
public class ProductDaoImp implements ProductDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Product> listProducts() {
		String sql= "select * from product";
		List<Product> products =jdbcTemplate.query(sql, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				return product;
			}
			
		});
		System.out.println(products.toString());
		
		System.out.println("Testing1Exit");
		return products;
	}

	@Override
	public void addProduct(Product product) {
		String sql= "insert into product (name, brand, size, category, availablequantity) VALUES (?,?,?,?,?)";
		System.out.println(product);
		int i = jdbcTemplate.update(sql, new Object[] {
				product.getName(),product.getBrand(),product.getSize(),product.getCategory(),product.getAvailablequantity()
		});
		
		if (i>0)
			System.out.println("Added successfull");
		else
			System.out.println("product add failed");
		
	}

	@Override
	public void deleteProduct(Product product) {
		String sql = "delete from product where productid=?";
		
		int i= jdbcTemplate.update(sql, new Object[] {
				product.getProductid()
		});
		
		if (i>0)
			System.out.println("product deleted successfully");
		else
			System.out.println("product deletion failed");
		
	}

	@Override
	public List<Product> searchProductsByCategory(String category) {
		String sql= "select * from product where category=?";
		@SuppressWarnings("deprecation")
		List<Product> products =jdbcTemplate.query(sql,new Object[] {category}, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				return product;
			}
			
		});
		System.out.println(products.toString());
		
		System.out.println("Testing1Exit");
		return products;
	}

	@Override
	public List<Product> searchProductsByName(String name) {
		String sql= "select * from product where name=?";
		@SuppressWarnings("deprecation")
		List<Product> products =jdbcTemplate.query(sql,new Object[] {name}, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				return product;
			}
			
		});
		System.out.println(products.toString());
		
		System.out.println("Testing1Exit");
		return products;
	}

	@Override
	public Product getProductsById(int productid) {
		String sql= "select * from product where productid=?";
		@SuppressWarnings("deprecation")
		List<Product> products =jdbcTemplate.query(sql,new Object[] {productid}, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
				return product;
			}
			
		});
		System.out.println(products.toString());
		
		Product product=null;
		for (int i=0;i<1 && products.size()>0;i++) {
			product=products.get(i);
		}
		
		return product;
	}

	@Override
	public void updateProductsById(Product product) {
		String sql = "update product set name=?,brand=?,size=?,category=?,availablequantity=? where productid=?";
		
		int i= jdbcTemplate.update(sql, new Object[] {
				product.getName(),product.getBrand(),product.getSize(),product.getCategory(),product.getAvailablequantity(),product.getProductid()
		});
		
		if (i>0)
			System.out.println("product updated successfull");
		else
			System.out.println("product update failed");
	}

}
