package com.example.domein;

import java.util.List;

public class Category {
	private Integer id;
	private String name;
	
	private List<Category> childCategories;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Category> getChildCategories() {
		return childCategories;
	}
	public void setChildCategories(List<Category> childCategories) {
		this.childCategories = childCategories;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", childCategories=" + childCategories + "]";
	}
	
	
	
}
