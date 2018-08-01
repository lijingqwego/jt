package com.jt.sso.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

@Service
public class UserService extends BaseService<User> {

	@Autowired
	private UserMapper userMapper;

	public Boolean check(String param, Integer type) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		switch (type) {
		case 1:
			paramMap.put("whereName", "username");
			break;
		case 2:
			paramMap.put("whereName", "phone");
			break;
		case 3:
			paramMap.put("whereName", "email");
			break;
		default:
			break;
		}
		paramMap.put("whereValue", param);
		return userMapper.check(paramMap) > 0 ? true : false;
	}
	
	
	public String register(User user){
		String password = user.getPassword();
		user.setPassword(DigestUtils.md5Hex(password));
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		try {
			userMapper.insertSelective(user);
		} catch (Exception e) {
			return null;
		}
		
		return user.getUsername();
	}
}
