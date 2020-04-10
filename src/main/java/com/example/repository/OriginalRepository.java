package com.example.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domein.Original;

/**
 * originalテーブルを表すリポジトリ.
 * @author fuka
 *
 */
@Repository
public class OriginalRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * Originalオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Original> ORIGINAL_ROW_MAPPER = (rs, i) -> {
		Original original = new Original();
		original.setTrainId(rs.getInt("train_id"));
		original.setName(rs.getString("name"));
		original.setItemConditionId(rs.getInt("item_condition_id"));
		original.setCategoryName(rs.getString("category_name"));
		original.setBrandName(rs.getString("brand_name"));
		original.setPrice(rs.getInt("price"));
		original.setShipping(rs.getInt("shipping"));
		original.setItemDescription(rs.getString("item_description"));
		return original;
	};
	
	/**
	 * IDから商品を検索します.
	 * @return 商品一覧
	 */
	public List<Original> findAll(){
		String sql = "SELECT train_id, name, item_condition_id, category_name, brand_name, price, shipping, item_description FROM original ORDER BY train_id limit 30";
		List<Original> originalList = template.query(sql, ORIGINAL_ROW_MAPPER);
		return originalList;
	}
	
	/**
	 * IDから商品を検索します.
	 * @param train_id
	 * @return
	 */
	public Original load(Integer train_id) {
		String sql = "SELECT train_id, name, item_condition_id, category_name, brand_name, price, shipping, item_description "
				+ " FROM original WHERE train_id = :train_id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("train_id", train_id);
		Original original = template.queryForObject(sql, param, ORIGINAL_ROW_MAPPER);
		return original;
	}
	
	/**
	 * 引数でもらった商品情報をoriginalテーブルに挿入します.
	 * @param original
	 */
	public void insert(Original original) {
		String sql ="INSERT INTO original (name, price, category_name, brand_name, item_condition_id, item_description) VALUES(:name, :price, :category_name, :brand_name, :item_condition_id, :item_description) ;" ;
		SqlParameterSource param = new BeanPropertySqlParameterSource(original);
		template.update(sql, param);
	}
	
	public void update(Original original) {
		String sql = "UPDATE original SET name = :name, price = :price, category_name = :category_name, brand_name = :brand_name, item_condition_id =:item_condition_id, item_description =:item_description ";
		SqlParameterSource param = new BeanPropertySqlParameterSource(original);
		template.update(sql, param);
	}
	
	
	
}
