package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domein.Category;

/**
 * 
 * (ページング関係ない)
 * @author fuka
 *
 */
@Repository
public class CategoryRepository {

	private static final RowMapper<Category> ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 親idの検索.
	 * @param parentId
	 * @return
	 */
	public List<Category> findByParentId(Integer parentId){
		MapSqlParameterSource param = new MapSqlParameterSource();
		String sql = "SELECT id, name FROM category";
		//親idがあったら、親idを指定する
		if (parentId != null) {
			sql += " WHERE parent_id = :parentId"; //SQLインジェクションのため２段階踏む
			param.addValue("parentId", parentId);
			//なければnull
		} else {
			sql += " WHERE parent_id is null";
		}
		sql += " ORDER BY id";
		return template.query(sql, param, ROW_MAPPER);
	}
	
}
