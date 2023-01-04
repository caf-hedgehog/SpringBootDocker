package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * originalレポジトリを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
@Repository
public class OriginalRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<String> CATEGORY_NAME_ROW_MAPPER = (rs, i) -> {
		return rs.getString("category_name");
	};

	/**
	 * 重複のないカテゴリ名を抽出.
	 * 
	 * @return カテゴリ名
	 */
	public List<String> findByUniqueCategoryName() {
		String sql = "SELECT DISTINCT category_name FROM original";
		List<String> categoryNames = template.query(sql, CATEGORY_NAME_ROW_MAPPER);
		return categoryNames;
	}

}
