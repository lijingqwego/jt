package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 通用跳转页面
 * @author 18679
 *
 */
@Controller
public class PageController {

	@RequestMapping("page/{pageName}")
	public String goHome(@PathVariable String pageName){
		return pageName;
	}
}
