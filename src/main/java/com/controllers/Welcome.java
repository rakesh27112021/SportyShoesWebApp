package com.controllers;

//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Welcome {
	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value={"/"})
	public ModelAndView adminLogin() {
		System.out.println("Testing");
		ModelAndView modelAndView= new ModelAndView("adminLogin");
		return(modelAndView);
	}
}