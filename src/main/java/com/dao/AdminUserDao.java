package com.dao;

import java.util.List;

import com.dto.AdminUser;
import com.dto.User;
public interface AdminUserDao {
	
	public Boolean checkUserExist();
	public void addAdminUser(AdminUser adminUser);
	public void updateAdminUserPassword(AdminUser adminUser);
	public Boolean validateAdminUser(AdminUser adminUser);
	public List<User> listUsers();

}
