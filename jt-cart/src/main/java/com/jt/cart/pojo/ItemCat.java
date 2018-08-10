package com.jt.cart.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JPA类和属�?�和表示表的字段映射
 * @author 18679
 *
 */
@Table(name="tb_item_cat")	//ItemCat类和tb_item_cat映射
public class ItemCat extends BasePojo{
	@Id//主键
	@GeneratedValue(strategy=GenerationType.IDENTITY)//自增主键
	private Long id;
	@Column(name="parent_id")//属�?�parentId和表的parent_id映射
	private Long parentId;
	private String name;
	private Integer status;
	private Integer sortOrder;
	private Boolean isParent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	//为EasyUI.tree组件
	public String getText(){
		return this.name;
	}

	public String getState(){
		return this.isParent ? "closed" : "open";
	}
}
