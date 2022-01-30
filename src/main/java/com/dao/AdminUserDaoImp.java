package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dto.AdminUser;
import com.dto.User;

@Repository("AdminUserDaoImp")
public class AdminUserDaoImp implements AdminUserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	
	@Override
	public void addAdminUser(AdminUser adminUser) {	
		String sql= "insert into admin_users values (?,?)";
		int i = jdbcTemplate.update(sql, new Object[] {
				adminUser.getUsername(),adminUser.getPassword()
		});
		
		if (i>0)
			System.out.println("update successfull");
		else
			System.out.println("update failed");
		
	}


	@Override
	public void updateAdminUserPassword(AdminUser adminUser) {
		String sql = "update admin_users set password=? where username=?";
		
		int i= jdbcTemplate.update(sql, new Object[] {
				adminUser.getPassword(),adminUser.getUsername()
		});
		
		if (i>0)
			System.out.println("password update successfull");
		else
			System.out.println("password update failed");
	}


	@Override
	public Boolean checkUserExist() {
		
		String sql = "select * from admin_users limit 1";
		List<AdminUser> adminUsers =jdbcTemplate.query(sql, new RowMapper<AdminUser>() {

			@Override
			public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdminUser adminUser = new AdminUser(rs.getString(1),rs.getString(2));
				return adminUser;
			}
			
		});
		
		if (adminUsers.size()>0) 
			return true;
		else
			return false;
	}


	@Override
	public Boolean validateAdminUser(AdminUser adminUser) {
		String sql = "select * from admin_users where username=? and password=?";
		
		@SuppressWarnings("deprecation")
		List<AdminUser> adminUsers =jdbcTemplate.query(sql, new Object[] {adminUser.getUsername(),adminUser.getPassword()} , new RowMapper<AdminUser>() {

			@Override
			public AdminUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdminUser adminUser = new AdminUser(rs.getString(1),rs.getString(2));
				return adminUser;
			}
			
		});
				
		System.out.println(adminUsers.size());
		if (adminUsers.size()>0) 
			return true;
		else
			return false;
	}


	@Override
	public List<User> listUsers() {
		String sql = "select * from users";
		
		List<User> users =jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				return user;
			}
			
		});
				
		System.out.println(users.size());
		
		return users;
		
	}

}
