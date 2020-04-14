package com.example.domein;

/**
 * Itemテーブルを表すドメインです.
 * @author fuka
 *
 */
public class Item {
	/** ID */
	private Integer id;
	/**　名前 */
	private String name;
	/**　商品状態ID */
	private Integer itemConditionId;
	/**　カテゴリーID */
	private Integer categoryId;
	/**　ブランド名 */
	private String brandName;
	/**　値段 */
	private Integer price;
	/**　出荷 */
	private Integer shipping;
	/**　商品説明 */
	private String itemDescription;
	/**　カテゴリー名 */
	private String nameAll;
	
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
	public Integer getItemConditionId() {
		return itemConditionId;
	}
	public void setItemConditionId(Integer itemConditionId) {
		this.itemConditionId = itemConditionId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getShipping() {
		return shipping;
	}
	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getNameAll() {
		return nameAll;
	}
	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", itemConditionId=" + itemConditionId + ", categoryId="
				+ categoryId + ", brandName=" + brandName + ", price=" + price + ", shipping=" + shipping
				+ ", itemDescription=" + itemDescription + ", nameAll=" + nameAll + "]";
	}
	public String getDaiCategoryName() {
        return nameAll != null ? nameAll.split("/")[0] : "";
    }

    public String getChuCategoryName() {
        return nameAll != null ? nameAll.split("/")[1] : "";
    }

    public String getSyoCategoryName() {
        return nameAll != null ? nameAll.split("/")[2] : "";
    }

	
	
}
