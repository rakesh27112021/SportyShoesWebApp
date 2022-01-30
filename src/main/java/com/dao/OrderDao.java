package com.dao;

import java.util.List;

import com.dto.Order;

public interface OrderDao {
	public void addOrder(Order order);
	public List<Order> listOrders();

}
