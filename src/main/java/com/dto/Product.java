package com.dto;

import org.springframework.stereotype.Component;

@Component
public class Product {
	private int productid;
	private String name;
	private String brand;
	private String size;
	private String category;
	private int availablequantity;
	public Product(int productid, String name, String brand, String size, String category, int availablequantity) {
		this.productid=productid;
		this.name=name;
		this.brand=brand;
		this.size=size;
		this.category=category;
		this.availablequantity=availablequantity;
		
	}
	
	public Product(int productid, String name, String brand, String size, String category, float availablequantity) {
		this.productid=productid;
		this.name=name;
		this.brand=brand;
		this.size=size;
		this.category=category;
		this.availablequantity=(int) availablequantity;
		
	}
	
	public Product(int productid, String name, String brand, String size, String category, String availablequantity) {
		this.productid=productid;
		this.name=name;
		this.brand=brand;
		this.size=size;
		this.category=category;
		this.availablequantity=Integer.parseInt(availablequantity);		
	}
	
	public Product() {
		System.out.println("product Deafult constructor");
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getAvailablequantity() {
		return availablequantity;
	}
	
	public void setAvailablequantity(int availablequantity) {
		this.availablequantity = availablequantity;
	}

	@Override
	public String toString() {
		return "Product [productid=" + productid + ", name=" + name + ", brand=" + brand + ", size=" + size
				+ ", category=" + category + ", availablequantity=" + availablequantity + "]";
	}
	
	

}
