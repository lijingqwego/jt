package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

@Service
public class ItemService extends BaseService<Item>{

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@PropertyConfig //读取配置文件中的值 并且将值赋值给这个变量
	public String IMAGE_BASE_URL;
	
	public EasyUIResult queryItemList(Integer page, Integer rows){
		PageHelper.startPage(page, rows, true);//开启分页
		List<Item> itemList = itemMapper.queryItemList();
		PageInfo<Item> pageInfo = new PageInfo<Item>(itemList);
		return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
	}
	
	
	/**
	 * 更新商品
	 * @param item
	 */
	public void saveItem(Item item,String itemDesc){
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		
		//mybatis框架将主键ID返回到item对象中了
		itemMapper.insertSelective(item);
		
		//商品详情新增
		ItemDesc desc = new ItemDesc();
		desc.setItemId(item.getId());
		desc.setItemDesc(itemDesc);
		desc.setCreated(item.getCreated());
		desc.setUpdated(item.getUpdated());
		
		itemDescMapper.insert(desc);
	}
	
	/**
	 * 修改商品
	 * @param item
	 */
	public void updateItem(Item item,String desc){
		//sSystem.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+IMAGE_BASE_URL);
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		
		//商品详情修改
		ItemDesc ItemDesc = new ItemDesc();
		ItemDesc.setItemId(item.getId());
		ItemDesc.setItemDesc(desc);
		ItemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKeySelective(ItemDesc);
	}
	
	/**
	 * 获取商品详情
	 * @param item
	 */
	public ItemDesc getItemDesc(Long itemId){
		return itemDescMapper.selectByPrimaryKey(itemId);
	}
	
	/**
	 * 级联删除  先删除子级ID，再删除父级ID
	 * @param ids
	 */
	public void deleteItems(String[] ids){
		itemDescMapper.deleteByIDS(ids);
		itemMapper.deleteByIDS(ids);
	}
	
	public Item getItemById(Long itemId){
		return itemMapper.selectByPrimaryKey(itemId);
	}
}
