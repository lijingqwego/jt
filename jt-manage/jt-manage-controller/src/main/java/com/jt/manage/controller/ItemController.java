package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.service.ItemService;

@RequestMapping("/item")
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult queryAll(Integer page,Integer rows){
		return itemService.queryItemList(page,rows);
	}
	
}
