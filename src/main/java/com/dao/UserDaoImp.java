package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dto.User;

@Repository("UserDaoImp")
public class UserDaoImp implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	
	@Override
	public void addUser(User user) {	
		String sql= "insert into users (useridentifier,firstname,lastname,password,email) values (?,?,?,?,?)";
		System.out.println(user.toString());
		int i = jdbcTemplate.update(sql, new Object[] {
				user.getUsername(),user.getUserfirstname(),user.getUserlastname(),user.getPassword(),user.getEmail()
		});
		
		if (i>0)
			System.out.println("update successfull");
		else
			System.out.println("update failed");
		
	}


	@Override
	public void updateUserPassword(User user) {
		String sql = "update users set password=? where username=?";
		
		int i= jdbcTemplate.update(sql, new Object[] {
				user.getPassword(),user.getUsername()
		});
		
		if (i>0)
			System.out.println("password update successfull");
		else
			System.out.println("password update failed");
	}


	@Override
	public Boolean checkUserExist(User user) {
		
		String sql = "select * from users where useridentifier=?";
		@SuppressWarnings("deprecation")
		List<User> users =jdbcTemplate.query(sql,new Object[] {user.getUsername()} ,new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return user;
			}
			
		});
		
		if (users.size()>0) 
			return true;
		else
			return false;
	}


	@Override
	public User validateUser(User user) {
		String sql = "select * from users where useridentifier=? and password=?";
		
		@SuppressWarnings("deprecation")
		List<User> users =jdbcTemplate.query(sql, new Object[] {user.getUsername(),user.getPassword()} , new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return user;
			}
			
		});
				
		System.out.println(users.size());
		if (users.size()>0) 
			return users.get(0);
		else
			return null;
	}
	
	@Override
	public User getUserById(String userid) {
		String sql = "select * from users where useridentifier=?";
		
		@SuppressWarnings("deprecation")
		List<User> users =jdbcTemplate.query(sql, new Object[] {userid} , new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),"");
				return user;
			}
			
		});
				
		System.out.println(users.size());
		if (users.size()>0) 
			return users.get(0);
		else
			return null;
	}


	@Override
	public User getUserById(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
