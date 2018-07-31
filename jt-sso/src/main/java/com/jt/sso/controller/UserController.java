package com.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping("/check/{param}/{type}")
	public SysResult check(@PathVariable("param")String param,@PathVariable("type")Integer type){
		Boolean check = userService.check(param, type);
		return SysResult.ok(check);
	}
	
	@ResponseBody
	@RequestMapping("/register")
	public SysResult register(User user){
		String username=userService.register(user);
		return SysResult.ok(username);
	}
}
