package com.jt.cart.mapper;

import java.util.List;

import com.jt.cart.pojo.ItemCat;
import com.jt.common.mapper.SysMapper;

public interface ItemCatMapper extends SysMapper<ItemCat> {
	
	public List<ItemCat> queryByPid(Integer pid);
}
