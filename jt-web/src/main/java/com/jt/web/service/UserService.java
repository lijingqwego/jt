package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jt.common.service.OkHttpClientService;
import com.jt.web.pojo.User;

@Service
public class UserService {
	
	@Autowired
	OkHttpClientService okHttpClientService;
	
	public String doRegister(User user) throws IOException{
		String url="http://localhost:8082/user/register";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("phone", user.getPhone());
//		params.put("email", user.getEmail());
		String jsonData = okHttpClientService.httpPost(url, params);
		JSONObject parseObject = JSON.parseObject(jsonData);
		String username = parseObject.getString("data");
		return username;
	}
	
	
	public String doLogin(String username,String password) throws IOException{
		String url="http://localhost:8082/user/login";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("u", username);
		params.put("p", password);
		String jsonData = okHttpClientService.httpPost(url, params);
		JSONObject parseObject = JSON.parseObject(jsonData);
		String ticket = parseObject.getString("data");
		return ticket;
	}

}
