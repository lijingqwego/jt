package com.jt.manage.controller;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.service.RedisService;
import com.jt.common.util.GsonUtil;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@RequestMapping("/item")
@Controller
public class ItemController {
	
	//引入Log4j
	private static final Logger log = Logger.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;
	@Autowired
	private RedisService redisService;
	
	private static final String item_key="item_";
	
	@RequestMapping("/query")
	@ResponseBody
	public EasyUIResult queryAll(Integer page,Integer rows){
		return itemService.queryItemList(page,rows);
	}
	
	/**
	 * 新增商品
	 * @param item
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveItem(Item item,String desc){
		try{
			itemService.saveItem(item,desc);
			log.info("新增商品成功！");
			return SysResult.ok();
		}catch(Exception e){
			log.error(e.getMessage());
			return SysResult.build(201, "新增商品失败,原因："+e.getMessage());
		}
	}
	
	/**
	 * 修改商品
	 * @param item
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public SysResult updateItem(Item item,String desc){
		try{
			itemService.updateItem(item,desc);
			log.info("修改商品成功！");
			return SysResult.ok();
		}catch(Exception e){
			log.error(e.getMessage());
			return SysResult.build(201, "修改商品失败,原因："+e.getMessage());
		}
	}
	
	/**
	 * 删除商品
	 * @param item
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public SysResult deleteItem(String[] ids){
		try{
			itemService.deleteItems(ids);
			log.info("删除商品成功！");
			return SysResult.ok();
		}catch(Exception e){
			log.error(e.getMessage());
			return SysResult.build(201, "删除商品失败,原因："+e.getMessage());
		}
	}
	
	/**
	 * 商品详情
	 * @param item
	 * @return
	 */
	@RequestMapping("/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult getItemDesc(@PathVariable Long itemId){
		ItemDesc itemDesc = itemService.getItemDesc(itemId);
		try {
			return SysResult.ok(itemDesc);
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	
	/**
	 * 商品详情
	 * @param item
	 * @return
	 */
	@RequestMapping("/items/{itemId}")
	@ResponseBody
	public SysResult getItem(@PathVariable Long itemId){
		Item item=null;
		String itemJson = redisService.get(item_key+itemId);
		if(StringUtils.isNotEmpty(itemJson))
		{
			log.debug("从缓存中读取。。。。");
			item = GsonUtil.GsonToBean(itemJson, Item.class);
		}
		else
		{
			log.debug("从数据库中读取。。。。");
			item = itemService.getItemById(itemId);
			redisService.set(item_key+itemId, GsonUtil.GsonString(item));
		}
		try {
			return SysResult.ok(item);
		} catch (Exception e) {
			log.error(e.getMessage());
			return SysResult.build(201, e.getMessage());
		}
	}
	
}
