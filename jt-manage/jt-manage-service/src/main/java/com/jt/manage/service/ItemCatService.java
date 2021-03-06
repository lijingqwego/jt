package com.jt.manage.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.RedisService;
import com.jt.common.util.GsonUtil;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;

@Service
public class ItemCatService extends BaseService<ItemCat>{

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Autowired
	private RedisService redisService;
	
	public List<ItemCat> queryByPid(Integer pid){
		String ITEM_CAT_KEY="ITEM_CAT_";
		String key=ITEM_CAT_KEY+pid;
		String value = redisService.get(key);
		if(StringUtils.isNotEmpty(value))
		{
			List<ItemCat> itemCatList = GsonUtil.GsonToList(value, ItemCat.class);
			return itemCatList;
		}
		else
		{
			List<ItemCat> list = itemCatMapper.queryByPid(pid);
			String json = GsonUtil.GsonString(list);
			redisService.set(key, json);
			return list;
		}
	}
}
