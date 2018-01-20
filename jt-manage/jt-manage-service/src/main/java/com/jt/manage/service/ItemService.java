package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;

@Service
public class ItemService extends BaseService<Item>{

	@Autowired
	private ItemMapper itemMapper;
	
	public EasyUIResult queryItemList(Integer page, Integer rows){
		PageHelper.startPage(page, rows, true);//开启分页
		List<Item> itemList = itemMapper.queryItemList();
		PageInfo<Item> pageInfo = new PageInfo<Item>(itemList);
		return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
	}
	
	
}
