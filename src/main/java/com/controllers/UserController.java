package com.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dao.OrderDaoImp;
import com.dao.ProductDaoImp;
import com.dao.UserDaoImp;
import com.dto.Order;
import com.dto.Product;
import com.dto.User;

@Controller
@RequestMapping(value="/user")
@SessionAttributes("UserName")
public class UserController {
	
	@Autowired
	UserDaoImp userDaoImp;
	
	@Autowired
	ProductDaoImp productDaoImp;
	
	@Autowired
	OrderDaoImp orderDaoImp;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView adminLogin(HttpSession session) {
		ModelAndView modelAndView = null;
		if (session.getAttribute("username")!=null) {
			modelAndView = new ModelAndView("dashboard");
		}else {
			modelAndView= new ModelAndView("login");
			//modelAndView= new ModelAndView("userSignUp");
		}
				
		return(modelAndView);
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public ModelAndView adminSignUp(HttpSession session) {
		ModelAndView modelAndView = null;
		if (session.getAttribute("username")!=null) {
			modelAndView = new ModelAndView("dashboard");
		}else {
			modelAndView= new ModelAndView("userSignUp");
		}
				
		return(modelAndView);
	}
	
	@RequestMapping(value="/registeruser",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView registerUser(HttpSession session, @ModelAttribute User user) {
		ModelAndView modelAndView = null;
		System.out.println(user.toString());
		
		if (userDaoImp.checkUserExist(user)) {
			System.out.println("Username already exists");
			modelAndView=new ModelAndView("registeruser");
			modelAndView.addObject(user);
			
		}else {
			userDaoImp.addUser(user);
			modelAndView=new ModelAndView("redirect:/user/login");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/validate", method=RequestMethod.GET)
	public ModelAndView validateUser(@RequestParam("username") String username,@RequestParam("password") String password,HttpSession session) {
		ModelAndView modelAndView = null;
		
		User user = new User(username,password);
		
		User sessionUser=userDaoImp.validateUser(user);
					
		System.out.println(sessionUser);
		if(sessionUser!=null && !sessionUser.getUsername().isEmpty())
		{
			modelAndView =new ModelAndView("redirect:/user/dashboard");
			
			session.setAttribute("user", sessionUser);
			session.setMaxInactiveInterval(600); 
		}
		else {
			modelAndView =new ModelAndView("login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/dashboard",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView userDashboard(HttpSession session) {
		ModelAndView modelAndView = null;
		User user=(User) session.getAttribute("user");
		
		if (user!=null && !user.getUsername().isEmpty() ) {
			modelAndView=new ModelAndView("dashboard");
			List<Product> products = productDaoImp.listProducts();
			modelAndView.addObject(user);
			modelAndView.addObject("products",products);
			
		}else {
			modelAndView=new ModelAndView("redirect:/user/login");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/neworder",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView newOrder(HttpSession session,@RequestParam("productid") String productid) {
		ModelAndView modelAndView = null;
		User user=(User) session.getAttribute("user");
		
		if (user!=null && !user.getUsername().isEmpty() ) {
			modelAndView=new ModelAndView("order");
			Product products = productDaoImp.getProductsById(Integer.parseInt(productid));
			Order order = new Order();
			order.setProduct(products);
			order.setQuantity(1);
			order.setUser(user);
			System.out.println(user);
			System.out.println(order);
			System.out.println("Testing order");
			modelAndView.addObject(user);
			modelAndView.addObject(order);
			
		}else {
			modelAndView=new ModelAndView("redirect:/user/login");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/saveorder",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView newOrder(HttpSession session,@ModelAttribute Order order,@ModelAttribute Product product) {
		ModelAndView modelAndView = null;
		User user=(User) session.getAttribute("user");
		
		if (user!=null && !user.getUsername().isEmpty() ) {
			System.out.println(product);
			System.out.println(order);
			order.setProduct(product);
			order.setUser(user);
			orderDaoImp.addOrder(order);
			System.out.println("After save");
			
			modelAndView=new ModelAndView("redirect:/user/dashboard");
			modelAndView.addObject(user);
			
		}else {
			modelAndView=new ModelAndView("redirect:/user/login");
		}
		
		return modelAndView;
	}

}
