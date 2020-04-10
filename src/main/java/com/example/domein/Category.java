package com.example.domein;

public class Category {
	private Integer id;
	private String name;
	private Integer parent_id;
	private String name_all;
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
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getName_all() {
		return name_all;
	}
	public void setName_all(String name_all) {
		this.name_all = name_all;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", parent_id=" + parent_id + ", name_all=" + name_all + "]";
	}
	
}
