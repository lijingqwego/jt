package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jt.common.service.OkHttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.web.pojo.Item;

@Service
public class ItemService {
//	@Autowired
//	private HttpClientService httpClientService;
	@Autowired
	private OkHttpClientService okHttpClientService;
	
	@PropertyConfig //读取配置文件中的值 并且将值赋值给这个变量
	public String MANAGE_URL;
	
	public Item getItemById(Long itemId) throws Exception{
		Item item = new Item();
		String url = MANAGE_URL+"/item/items/"+itemId;
//		String jsonData = httpClientService.doGet(url, "utf-8");
		String jsonData = okHttpClientService.httpGet(url);
		JSONObject parseObject = JSON.parseObject(jsonData);
		if(parseObject.isEmpty())
		{
			return item;
		}
		JSONObject jsonObject = parseObject.getJSONObject("data");
		if(!jsonObject.isEmpty())
		{
			item.setCid(jsonObject.getLong("cid"));
			item.setId(jsonObject.getLong("id"));
			item.setImage(jsonObject.getString("image"));
			item.setNum(jsonObject.getInteger("num"));
			item.setPrice(jsonObject.getLong("price"));
			item.setSellPoint(jsonObject.getString("sellPoint"));
			item.setStatus(jsonObject.getInteger("status"));
			item.setTitle(jsonObject.getString("title"));
		}
		return item;
	}
}
