package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;

@Service
public class ItemService {
	@Autowired
	private HttpClientService httpClientService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public Item getItemById(Long itemId) throws Exception{
		//通过httpClient发起http请求，请求后台
		String url = "http://manage.jt.com/web/item/"+itemId;
		//注意有超时的问题，
		String jsonData = httpClientService.doGet(url, "utf-8");
		
		//把json串转成单个pojo对象
		Item item = MAPPER.readValue(jsonData, Item.class);
		return item;
	}
}
