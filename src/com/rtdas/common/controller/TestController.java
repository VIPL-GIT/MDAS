package com.rtdas.common.controller;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rtdas.common.models.Users;

@Controller
public class TestController {
	@RequestMapping(value="/index" ,method=RequestMethod.GET)
	public ModelAndView test(@ModelAttribute("command") Users users, 
			BindingResult result){
//		System.out.println(paramName);
		System.out.println("***********:Welcome Page.......");
		return new ModelAndView("login");
	}
@RequestMapping(value="/home" ,method=RequestMethod.GET)
public ModelAndView getHomePage(){
	return new ModelAndView("home");
	
}

@RequestMapping(value="/addUser" ,method=RequestMethod.GET)
public ModelAndView addUser(){
	return new ModelAndView("user/addUser");
}
}
	