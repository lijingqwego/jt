package com.jt.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	
	@ResponseBody
	@RequestMapping("/doRegister")
	public SysResult doRegister(User user) throws IOException{
		String username = userService.doRegister(user);
		return SysResult.ok(username);
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
	public SysResult doLogin(String username,String password,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		String ticket = userService.doLogin(username, password);
		CookieUtils.setCookie(request, response, "JT_TICKET", ticket);
		return SysResult.ok(ticket);
	}
}