package com.example.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.controller.ShowItemListController;
import com.example.domein.Item;
import com.example.form.SearchForm;

/**
 * itemテーブルを表すリポジトリ.
 * 
 * @author fuka
 *
 */
@Repository
public class ItemRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemRepository.class);

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
//	public List<Item> findAll(){
//		String sql = "SELECT i.id, i.name, i.category_id, i.shipping, i.item_description, i.price, c.name_all, i.brand_name, i.item_condition_id FROM items i INNER JOIN category c ON i.category_id = c.id ORDER BY i.id limit 30";
//		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
//		return itemList;
//	}
	
	/**
	 * IDから商品情報を検索します.(商品詳細表示)
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
	 * 商品登録用.addページ
	 * @param item
	 */
	public void insert(Item item) {
		String sql = "INSERT INTO items (name, item_condition_id, category_id, brand_name, price, shipping, item_description) VALUES(:name, :item_condition_id, :category_id, :brand_name, :price, :shipping, :item_description) ;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);
	}
	
	
	/**
	 * 普通の検索機能(ページング機能にも使用)
	 * @param searchForm
	 * @return
	 */
	public List<Item> search(SearchForm searchForm) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String sql = createSql(searchForm, param, null);
		return template.query(sql, param, ITEM_ROW_MAPPER);
	}
	
	/**
	 * 検索にヒットしたデータを数えるメソッド(ページング機能にも使用)
	 * @param searchForm
	 * @return
	 */
	public Integer searchCount(SearchForm searchForm) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String sql = createSql(searchForm, param, "count");
		return template.queryForObject(sql, param, Integer.class);
	}
	
	//先に作る(万能なsql)
	private String createSql(SearchForm searchForm, MapSqlParameterSource param, String mode) {
		
		String sql = "SELECT i.id id, i.name \"name\", item_condition_id, category_id, brand_name, price, shipping, item_description, name_all"
				+ " FROM items i LEFT JOIN category c ON c.id  = i.category_id "
				+ " WHERE 1 = 1";
		
		//カテゴリー
		if (!StringUtils.isEmpty(searchForm.getCategoryName())) {
			sql += " AND name_all LIKE :name_all";
			param.addValue("name_all", searchForm.getCategoryName() + "%");
		}
		
		//商品名(曖昧検索)
		if (!StringUtils.isEmpty(searchForm.getItemKeyword())) {
			sql += " AND i.name LIKE :name";
			param.addValue("name", "%" + searchForm.getItemKeyword() + "%");
		}
		
		//ブランド名
		if (!StringUtils.isEmpty(searchForm.getBrand())) {
			sql += " AND brand_name = :brand";
			param.addValue("brand", searchForm.getBrand());
		}
		
		//modeがcountだったら、SELECT〜FROMまでをSELECT count(*) FROMに置き換える
		if ("count".equals(mode)) {
			sql = sql.replaceFirst("SELECT.+FROM", "SELECT count(*) FROM");
		
		}else {
			sql += " ORDER BY i.id";
			sql += " LIMIT 30 OFFSET " + ShowItemListController.ROW_PAR_PAGE * (searchForm.getPage() -1);
			//(1ページあたりの表示件数(30) × (検索ページ番号 -1)
		}
		logger.info("sql =" + sql);
		for (String paramName : param.getParameterNames()) {
			logger.info(paramName + " = " + param.getValue(paramName));
		}
		
		return sql;
		
	}
	
	
	
	
	
}
