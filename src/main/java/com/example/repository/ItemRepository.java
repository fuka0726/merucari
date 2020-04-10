package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domein.Item;

/**
 * itemテーブルを表すリポジトリ.
 * 
 * @author fuka
 *
 */
@Repository
public class ItemRepository {

	@Autowired 
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * Itemオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setItemConditionId(rs.getInt("item_condition_id"));
		item.setCategoryId(rs.getInt("category_id"));
		item.setBrandName(rs.getString("brand_name"));
		item.setPrice(rs.getInt("price"));
		item.setShipping(rs.getInt("shipping"));
		item.setItemDescription(rs.getString("item_description"));
		item.setNameAll(rs.getString("name_all"));
		return item;
	};
	
	/**
	 * IDから商品を検索します.
	 *
	 * @return 商品一覧
	 */
	public List<Item> findAll(){
		String sql = "SELECT i.id, i.name, i.category_id, i.shipping, i.item_description, i.price, c.name_all, i.brand_name, i.item_condition_id FROM items i INNER JOIN category c ON i.category_id = c.id ORDER BY i.id limit 30";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * IDから商品情報を検索します.
	 * @param id
	 * @return
	 */
	public Item load(Integer id) {
		String sql = "SELECT i.id, i.name, i.price, i.category_id, i.shipping, c.name_all, i.brand_name, i.item_condition_id, i.item_description FROM items i INNER JOIN category c ON i.category_id = c.id WHERE i.id = :i.id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("i.id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
	
	/**
	 * @param item
	 */
	public void insert(Item item) {
		String sql = "INSERT INTO items (name, item_condition_id, category_id, brand_name, price, shipping, item_description) VALUES(:name, :item_condition_id, :category_id, :brand_name, :price, :shipping, :item_description) ;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);
	}
	
	
	
	
	
}
