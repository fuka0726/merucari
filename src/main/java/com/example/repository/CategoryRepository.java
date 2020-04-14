package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domein.Category;

public class CategoryRepository {

	private static final RowMapper<Category> ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public List<Category> findByParentId(Integer parentId){
		MapSqlParameterSource param = new MapSqlParameterSource();
		String sql = "SELECT id, name FROM category";
		if (parentId != null) {
			sql += " WHERE parent_id = :parentId";
			param.addValue("parentId", parentId);
		} else {
			sql += " WHERE parent_id is null";
		}
		sql += " ORDER BY id";
		return template.query(sql, param, ROW_MAPPER);
	}
	
}
