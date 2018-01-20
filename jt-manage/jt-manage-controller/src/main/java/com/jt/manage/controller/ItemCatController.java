package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;

@RequestMapping("/item/cat")
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/all")
	@ResponseBody//自动转成json
	public List<ItemCat> queryAll(){
		List<ItemCat> list = itemCatService.queryAll();
		return list;
	}
	
	/**
	 * 获取商品分类的json串
	 * @param pid
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody	//EasyUI异步加载树，点击节点是，会自动把当前节点的值作为参数id传递
	public List<ItemCat> queryByPid(@RequestParam(defaultValue="0")Integer id){
		return itemCatService.queryByPid(id);
	}
	
}
