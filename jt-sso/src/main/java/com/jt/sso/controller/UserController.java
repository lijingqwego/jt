package com.jt.sso.controller;

import org.apache.commons.codec.language.RefinedSoundex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RedisService redisService;

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
	
	@ResponseBody
	@RequestMapping("/login")
	public SysResult login(String u,String p){
		User _user = new User();
		_user.setUsername(u);
		_user.setPassword(p);
		String ticket = userService.login(_user);
		return SysResult.ok(ticket);
	}
	
	@ResponseBody
	@RequestMapping("/query/{ticket}")
	public SysResult queryTicket(@PathVariable String ticket){
		String userJson = redisService.get(ticket);
		return SysResult.ok(userJson);
	}
}
