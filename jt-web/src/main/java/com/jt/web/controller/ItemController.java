package com.jt.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.common.service.RedisService;
import com.jt.common.util.GsonUtil;
import com.jt.web.pojo.Item;
import com.jt.web.service.ItemService;

@Controller
public class ItemController {
	
	private static final Logger log = Logger.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private RedisService redisService;
	private static final String item_key="item_";
	
	//http://www.jt.com/items/562379.html
	@RequestMapping("/items/{itemId}")
	public String items(@PathVariable Long itemId, Model model) throws Exception{
		Item item=null;
		String itemJson = redisService.get(item_key+itemId);
		if(StringUtils.isNotEmpty(itemJson))
		{
			log.debug("前台执行从缓存中读取。。。。");
			item = GsonUtil.GsonToBean(itemJson, Item.class);
		}
		else
		{
			log.debug("前台执行从后台中读取。。。。");
			item = itemService.getItemById(itemId);
			redisService.set(item_key+itemId, GsonUtil.GsonString(item));
		}
		model.addAttribute("item", item);
		
		return "item";
	}
}
