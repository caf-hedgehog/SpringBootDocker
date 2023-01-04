package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.form.SearchForm;

/**
 * itemsテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	/**
	 * 商品全検索.
	 * 
	 * @return 商品リスト
	 */
	public List<Item> findAll() {
		String sql = "select a.id, a.name, a.condition_id, b.id as category_id, a.brand, a.price, a.shipping, a.description from original as a left outer join category as b on a.category_name = b.name_all";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 商品条件検索.
	 * 
	 * @param form         フォーム
	 * @param id           商品ID
	 * @param categoryName カテゴリ名
	 * @return 商品リスト
	 */
	public List<Item> findBySearchForm(SearchForm form, Integer id, String categoryName) {
		String sql = "select a.id, a.name, a.conditioned as condition_id, b.name as small, (select name from category where id = b.parent) as middle,(select name from category where id = (select parent from category where id = b.parent) ) as big, a.brand, a.price, a.shipping, a.description from items as a left outer join category as b on a.category = b.id";

		// categoryはあいまい検索で行けるのでは
		boolean flagLikeName = false;
		boolean flagLikeCategoryName = false;

		// 名前のあいまい検索
		if (!form.getName().equals("")) {
			String likeName = String.format(" a.name LIKE '%%%S%%'", form.getName()); // "name ILIKE :name";
			sql += " where" + likeName;
			flagLikeName = true;
		}

		// カテゴリ検索
		if (categoryName != null) {
			String likeCategoryName = String.format(" b.name_all LIKE '%%%s%%'", categoryName);
			if (flagLikeName) {
				sql += " and" + likeCategoryName; // 名前検索も行われていた場合
			} else {
				sql += " where" + likeCategoryName; // 名前検索が行われていない場合
			}
			flagLikeCategoryName = true;
		}

		// ブランド名検索
		if (!form.getBrand().equals("")) {
			String likeBrandName = String.format(" a.brand LIKE '%s%%'", form.getBrand());
			if (flagLikeName || flagLikeCategoryName) {
				sql += " and" + likeBrandName; // 名前検索かカテゴリ検索が行われていた場合
			} else {
				sql += " where" + likeBrandName;
			}
		}
		String limit = String.format(" LIMIT %d,30", id); // "LIMIT 1,30";
		sql += limit;
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 商品条件検索.
	 * 
	 * @param form         フォーム
	 * @param id           商品ID
	 * @param categoryName カテゴリ名
	 * @return 商品リスト
	 */
	public Integer countSearch(SearchForm form, Integer id, String categoryName) {
		String countSql = "select count(a.id) from items as a left outer join category as b on a.category = b.id";

		// categoryはあいまい検索で行けるのでは
		boolean flagLikeName = false;
		boolean flagLikeCategoryName = false;

		// 名前のあいまい検索
		if (!form.getName().equals("")) {
			String likeName = String.format(" a.name LIKE '%%%S%%'", form.getName()); // "name ILIKE :name";
			countSql += " where" + likeName;
			flagLikeName = true;
		}

		// カテゴリ検索
		if (categoryName != null) {
			String likeCategoryName = String.format(" b.name_all LIKE '%s%%'", categoryName);
			if (flagLikeName) {
				countSql += " and" + likeCategoryName; // 名前検索も行われていた場合
			} else {
				countSql += " where" + likeCategoryName; // 名前検索が行われていない場合
			}
			flagLikeCategoryName = true;
		}

		// ブランド名検索
		if (!form.getBrand().equals("")) {
			String likeBrandName = String.format(" a.brand LIKE '%s%%'", form.getBrand());
			if (flagLikeName || flagLikeCategoryName) {
				countSql += " and" + likeBrandName; // 名前検索かカテゴリ検索が行われていた場合
			} else {
				countSql += " where" + likeBrandName;
			}
		}
		Integer num = template.getJdbcTemplate().queryForObject(countSql, Integer.class);
		return num;
	}

	/**
	 * 商品検索.
	 * 
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item findById(Integer id) {
		String sql = "SELECT a.id, a.name, a.conditioned as condition_id, b.name_all, a.brand, a.price, a.shipping, a.description FROM items as a left outer join category as b on a.category = b.id WHERE a.id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}

	/**
	 * ３０件ごとに商品取得.
	 * 
	 * @param id ページ数
	 * @return 商品リスト
	 */
	public List<Item> findByPageId(Integer id) {
		String sql = "select a.id, a.name, a.conditioned, b.name as small, (select name from category where id = b.parent) as middle,(select name from category where id = (select parent from category where id = b.parent) ) as big, a.brand, a.price, a.shipping, a.description from items as a left outer join category as b on a.category = b.id LIMIT :id,30";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * itemテーブルデータ件数取得.
	 * 
	 * @return 件数
	 */
	public Integer countById() {
		String sql = "select count(id) from items";
		Integer num = template.getJdbcTemplate().queryForObject(sql, Integer.class);
		return num;
	}

	/**
	 * 商品情報の挿入.
	 * 
	 * @param item 商品フォーム
	 * @return 商品情報
	 */
	public Item insert(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		if (item.getId() == null) {
			String sql = "INSERT INTO items(name, conditioned, category, brand, price, shipping, description) VALUES(:name, :condition_id, :category_id, :brand, :price, :shipping, :description)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String keyColumnNames[] = { "id" };
			template.update(sql, param, keyHolder, keyColumnNames);
			item.setId(keyHolder.getKey().intValue());
		} else {

		}
		return item;
	}

	/**
	 * ユーザー情報の変更
	 * 
	 * @param user ユーザー情報
	 */
	public void update(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		String sql = "UPDATE items SET name=:name, conditioned=:condition_id, category=:category_id, brand=:brand, price=:price, shipping=:shipping, description=:description WHERE id=:id";
		template.update(sql, param);
	}
}
