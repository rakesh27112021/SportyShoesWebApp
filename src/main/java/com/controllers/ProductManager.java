package com.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.OrderDaoImp;
import com.dao.ProductDaoImp;
import com.dto.Order;
import com.dto.Product;

@Controller
@RequestMapping(value="/productmanager")
public class ProductManager {
	
	@Autowired
	ProductDaoImp productDaoImp;
	
	@Autowired
	OrderDaoImp orderDaoImp;
	
	@RequestMapping(value="/all")
	public ModelAndView listProducts(HttpSession session,@ModelAttribute Product product) {
		ModelAndView modelAndView=null;
		if (session.getAttribute("adminUserName")!=null) {
			if(product.getName()!=null && !product.getName().isEmpty()) {
				System.out.println("productcheck: "+product);
				List <Product> products= productDaoImp.searchProductsByName(product.getName());
				modelAndView=new ModelAndView("products");
				modelAndView.addObject("products",products);
			}else if(product.getCategory()!=null && !product.getCategory().isEmpty()) {
				System.out.println("productcheck: "+product);
				List <Product> products= productDaoImp.searchProductsByCategory(product.getCategory());
				modelAndView=new ModelAndView("products");
				modelAndView.addObject("products",products);
			}else {
				List<Product> products = productDaoImp.listProducts();
				System.out.println("Testing2");
				modelAndView = new ModelAndView("products");
				products.toString();
				modelAndView.addObject("products",products);
				modelAndView.addObject("Testing","Hey there");
			
			}
		}else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/addproduct",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView addNewProduct(HttpSession session, @ModelAttribute Product product) {
		ModelAndView modelAndView = null;
		System.out.println(product.toString());
		
		if (session.getAttribute("adminUserName")!=null) {
			if (product.getName()==null) {
				modelAndView=new ModelAndView("product");
			}else {
				productDaoImp.addProduct(product);
				modelAndView=new ModelAndView("redirect:/productmanager/all");
				//modelAndView.addObject("product",product);
			}
				
			
		}else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/update",method= {RequestMethod.GET})
	public ModelAndView updateProduct(HttpSession session, @RequestParam("productid") int productid ) {
		ModelAndView modelAndView = null;
		
		if (session.getAttribute("adminUserName")!=null) {
			if (productid!=0) {
					Product productbyid=productDaoImp.getProductsById(productid);
					modelAndView=new ModelAndView("updateProduct");
					modelAndView.addObject("product",productbyid);
				
			}else {
				modelAndView=new ModelAndView("redirect:/productmanager/all");
			}
				
			
		}else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/update",method= {RequestMethod.POST})
	public ModelAndView updateProduct(HttpSession session,@ModelAttribute Product product ) {
		ModelAndView modelAndView = null;
		
		if (session.getAttribute("adminUserName")!=null) {
			if (product.getProductid()!=0) {
				System.out.println("productcheck: "+product);
				productDaoImp.updateProductsById(product);
				modelAndView=new ModelAndView("updateProduct");
				modelAndView.addObject("product",product);
				}else {
				modelAndView=new ModelAndView("redirect:/productmanager/all");

			}
				
			
		}else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/delete",method= {RequestMethod.GET})
	public ModelAndView deleteProduct(HttpSession session,@ModelAttribute Product product ) {
		ModelAndView modelAndView = null;
		
		if (session.getAttribute("adminUserName")!=null) {
			if (product.getProductid()!=0) {
				productDaoImp.deleteProduct(product);
				modelAndView=new ModelAndView("products");
				List<Product> products = productDaoImp.listProducts();
				modelAndView.addObject("products",products);
				}else {
				modelAndView=new ModelAndView("redirect:/productmanager/all");

			}
				
			
		}else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/orders")
	public ModelAndView listOrders(HttpSession session) {
		ModelAndView modelAndView=null;
		if (session.getAttribute("adminUserName")!=null) {
			List<Order> orders = orderDaoImp.listOrders();
			System.out.println("Testing2");
			modelAndView = new ModelAndView("orders");
			orders.toString();
			modelAndView.addObject("orders",orders);
			
		}else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/product/search",method= {RequestMethod.GET})
	public ModelAndView searchProduct(HttpSession session,@ModelAttribute Product product ) {
		ModelAndView modelAndView = null;
		
		System.out.println("Test: " + product);
		if (session.getAttribute("adminUserName")!=null) {
			if (!product.getName().isEmpty()) {
				System.out.println("productcheck: "+product);
				List <Product> products= productDaoImp.searchProductsByName(product.getName());
				modelAndView=new ModelAndView("products");
				modelAndView.addObject("products",products);
				}else {
				modelAndView=new ModelAndView("redirect:/productmanager/all");

			}
				
			
		}else {
			modelAndView = new ModelAndView("redirect:/admin/login");
		}
		return modelAndView;
	}

}
