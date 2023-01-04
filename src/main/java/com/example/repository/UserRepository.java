package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * usersテーブルを操作するレポジトリ.
 * 
 * @author 萩田
 *
 */
@Repository
public class UserRepository {

	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザー検索.
	 * 
	 * @param id ユーザーID
	 * @return ユーザー情報
	 */
	public User load(Integer id) {
		String sql = "SELECT name, password, email, authority FROM users WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}

	/**
	 * ユーザー登録.
	 * 
	 * @param user ユーザー情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, password, email, authority) VALUES(:name, :password, :email, :authority)";
		template.update(sql, param);
	}

	/**
	 * ユーザー検索.
	 * 
	 * @param email メールアドレス
	 * @return ユーザー情報
	 */
	public User findByEmail(String email) {
		String sql = "SELECT name, password, email,authority FROM users WHERE email = email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}

}
