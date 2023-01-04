package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

/**
 * categoryテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<String[]> CATEGORY_NAME_LIST_ROW_MAPPER = (rs, i) -> {
		String[] categoryNames = { rs.getString("id"), rs.getString("name") };
		return categoryNames;
	};

	private static final RowMapper<Integer> CATEGORY_ID_ROW_MAPPER = (rs, i) -> {
		return rs.getInt("id");
	};

	private static final RowMapper<Integer> CATEGORY_PARENT_ID_ROW_MAPPER = (rs, i) -> {
		return rs.getInt("parent");
	};

	private static final RowMapper<String> CATEGORY_NAME_ROW_MAPPER = (rs, i) -> {
		return rs.getString("name");
	};

	/**
	 * カテゴリー挿入.
	 * 
	 * @param category カテゴリー
	 * @return カテゴリid
	 */
	public Integer insert(Category category) {
		String sql = "INSERT INTO category (parent,name,name_all) VALUES(:parent,:name,:nameAll)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String keyColumnNames[] = { "id" };
		template.update(sql, param, keyHolder, keyColumnNames);
		Integer id = keyHolder.getKey().intValue();
		return id;
	}

	/**
	 * カテゴリ名検索.
	 * 
	 * @return カテゴリーネーム
	 */
	public List<String[]> searchBigCategory() {
		String sql = "select id, name from category where parent is null";
		List<String[]> categoryNameList = template.query(sql, CATEGORY_NAME_LIST_ROW_MAPPER);
		return categoryNameList;
	}

	/**
	 * カテゴリ名検索.
	 * 
	 * @return カテゴリーネーム
	 */
	public List<String[]> searchByParentId(Integer parent) {
		String sql = "select id, name from category where parent = :parent";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parent", parent);
		List<String[]> categoryNameList = template.query(sql, param, CATEGORY_NAME_LIST_ROW_MAPPER);
		return categoryNameList;
	}

	/**
	 * 親IDを検索する.
	 * 
	 * @param id カテゴリID
	 * @return 親ID
	 */
	public Integer findById(Integer id) {
		String sql = "select parent from category where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Integer> cagetoryId = template.query(sql, param, CATEGORY_PARENT_ID_ROW_MAPPER);
		return cagetoryId.get(0);
	}

	/**
	 * 小項目IDを検索.
	 * 
	 * @param categoryName カテゴリ名
	 * @return 小項目ID
	 */
	public Integer findByCategoryName(String categoryName) {
		String sql = "select id from category where name_all = :categoryName";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName", categoryName);
		List<Integer> cagetoryId = template.query(sql, param, CATEGORY_ID_ROW_MAPPER);
		return cagetoryId.get(0);
	}

	/**
	 * カテゴリ名検索.
	 * 
	 * @param id カテゴリID
	 * @return カテゴリ名
	 */
	public String findByCategoryID(Integer id) {
		String sql = "SELECT name from category WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<String> categoryName = template.query(sql, param, CATEGORY_NAME_ROW_MAPPER);
		return categoryName.get(0);
	}

}
