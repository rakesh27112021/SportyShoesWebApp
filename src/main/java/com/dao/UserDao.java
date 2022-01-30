package com.dao;

import com.dto.User;
public interface UserDao {
	public Boolean checkUserExist(User user);
	public void addUser(User user);
	public void updateUserPassword(User user);
	public User validateUser(User user);
	public User getUserById(int userid);
	public User getUserById(String userid);

}

