package com.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AdminUserDaoImp;
import com.dto.AdminUser;
import com.dto.Product;
import com.dto.User;

@Controller
@RequestMapping(value="/admin")
@SessionAttributes("adminUserName")
public class AdminLogin {
	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	
	@Autowired
	AdminUserDaoImp adimUserDaoImp; 

	
	@RequestMapping(value={"/login"},method=RequestMethod.GET)
	public ModelAndView adminLogin() {
		ModelAndView modelAndView= new ModelAndView("adminLogin");
		return(modelAndView);
	}
	
	@RequestMapping(value="/validate", method=RequestMethod.GET)
	public ModelAndView validateUser(@RequestParam("username") String username,@RequestParam("password") String password,HttpSession session) {
		ModelAndView modelAndView = null;
		
		AdminUser adminUser = new AdminUser(username,password);
		
		if (adimUserDaoImp.checkUserExist()==false)
			adimUserDaoImp.addAdminUser(new AdminUser("Admin","Admin123"));
		
		System.out.println(adimUserDaoImp.validateAdminUser(adminUser));
					
		if(adimUserDaoImp.validateAdminUser(adminUser))
		{
			modelAndView =new ModelAndView("redirect:/admin/dashboard");
			
			session.setAttribute("adminUserName", adminUser.getUsername());
			session.setMaxInactiveInterval(600); 
		}
		else
			modelAndView =new ModelAndView("adminLogin");
		return modelAndView;
	}
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public ModelAndView dashboard(HttpSession session) {
		ModelAndView modelAndView = null;
		System.out.println(session.getAttribute("dashboard:" + session.getAttribute("adminUserName") + session.getId()+session.getAttributeNames()));
		if (session.getAttribute("adminUserName")!=null)
		{
			modelAndView = new ModelAndView("adminDashboard");
			System.out.println(session.getId());
			System.out.println(session.getAttribute("adminUserName"));
			System.out.println(session.getCreationTime());
			System.out.println(session.getMaxInactiveInterval());
			
		}
		else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		
		return modelAndView;
	}

	@RequestMapping(value="/updatepassword", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updateAdminPassword(@RequestParam Map<String,String> Passwords,HttpSession session) {
		ModelAndView modelAndView = null;
		
		System.out.println("here update passowrd");
		
		if (session.getAttribute("adminUserName")!=null) {
			modelAndView = new ModelAndView("updateAdminUser");
			if ((Passwords.get("newPassword")!=null || Passwords.get("confirmNewPassword")!=null) && Passwords.get("newPassword").toString().length()>0) {
				if (Passwords.get("newPassword").equals(Passwords.get("confirmNewPassword"))) {
					AdminUser adminUser = new AdminUser((String) session.getAttribute("adminUserName"),Passwords.get("newPassword"));
					adimUserDaoImp.updateAdminUserPassword(adminUser);
					modelAndView.addObject("responsemsg","password updated Successfully");
				}else {
					modelAndView.addObject("responsemsg","new and confirm password do not match");
				}		
				
			}			
		}
			
		else {
			modelAndView = new ModelAndView("redirect:/admin/login");
			modelAndView.addObject("sessionStatusMsg","Session timeout");
		}
		
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/userslist")
	public ModelAndView listUsers(HttpSession session) {
		ModelAndView modelAndView=null;
		if (session.getAttribute("adminUserName")!=null) {
			List<User> users = adimUserDaoImp.listUsers();
			System.out.println("Testing2");
			modelAndView = new ModelAndView("users");
			users.toString();
			modelAndView.addObject("users",users);
			modelAndView.addObject("Testing","Hey there");
		}else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		return modelAndView;
	}
	
}
