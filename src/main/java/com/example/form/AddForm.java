package com.example.form;

public class AddForm {

	/** 名前 */
	private String name;
	/** 値段 */
	private String price;
	/** 親カテゴリ */	
	private String parentCategory;
	/** 子カテゴリ */
	private String childCategory;
	/** 孫カテゴリ */
	private String grandChild;
	/** ブランド名 */
	private String brandName;
	/** 商品状態id */
	private String itemConditionId;
	/** 商品説明 */
	private String itemDescription;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getChildCategory() {
		return childCategory;
	}
	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}
	public String getGrandChild() {
		return grandChild;
	}
	public void setGrandChild(String grandChild) {
		this.grandChild = grandChild;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getItemConditionId() {
		return itemConditionId;
	}
	public void setItemConditionId(String itemConditionId) {
		this.itemConditionId = itemConditionId;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	@Override
	public String toString() {
		return "AddForm [name=" + name + ", price=" + price + ", parentCategory=" + parentCategory + ", childCategory="
				+ childCategory + ", grandChild=" + grandChild + ", brandName=" + brandName + ", itemConditionId="
				+ itemConditionId + ", itemDescription=" + itemDescription + "]";
	}
	
	
}
