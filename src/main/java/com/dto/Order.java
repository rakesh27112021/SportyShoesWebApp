package com.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Order {
	private String orderid;
	private Product product;
	private int quantity;
	private Date orderDate;
	private User user;
	private String status;
	
	
	
	public Order(int orderId, Product product, String quantity, Date orderDate,
			User user, String status) {
		this.orderid=String.valueOf(orderId);
		this.product=product;
		this.quantity=Integer.parseInt(quantity);
		this.orderDate=orderDate;
		this.user=user;
		this.status=status;
		
		
		// TODO Auto-generated constructor stub
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Order(String orderid) {
		this.orderid=orderid;
		
		// TODO Auto-generated constructor stub
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product prudct) {
		this.product = prudct;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", prudct=" + product + ", quantity=" + quantity + ", orderDate="
				+ orderDate + ", user=" + user + ", status=" + status + "]";
	}
	
	
	
}
