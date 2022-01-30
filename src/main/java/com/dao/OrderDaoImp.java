package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dto.Order;

@Repository("OrderDaoImp")
public class OrderDaoImp implements OrderDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ProductDaoImp productDaoImp;
	
	@Autowired
	UserDaoImp userDaoImp;

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		String sql= "insert into orders (productid, quantity, orderdate, userid, status) VALUES (?,?,SYSDATE(),?,?)";
		int i = jdbcTemplate.update(sql, new Object[] {
				order.getProduct().getProductid(),order.getQuantity(),order.getUser().getUsername(),""
		});
		
		if (i>0)
			System.out.println("update successfull");
		else
			System.out.println("update failed");
		
	}

	@Override
	public List<Order> listOrders() {
		String sql= "select * from orders";
		List<Order> orders =jdbcTemplate.query(sql, new RowMapper<Order>() {

			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {	
				System.out.println("Inside list order 0");
				System.out.println(productDaoImp.getProductsById(Integer.parseInt(rs.getString(2))));
				System.out.println(userDaoImp.getUserById(rs.getString(7)));
				Order order = new Order(rs.getInt(1),productDaoImp.getProductsById(Integer.parseInt(rs.getString(2))),rs.getString(3),
				
						rs.getDate(5),userDaoImp.getUserById(rs.getString(7)) ,rs.getString(8));
				
				//Order order = new Order(rs.getString(1));
				System.out.println("Inside list order");
				return order;
			}
			
		});
		System.out.println(orders.toString());
		
		System.out.println("Testing1Exit");
		return orders;
	}

}
