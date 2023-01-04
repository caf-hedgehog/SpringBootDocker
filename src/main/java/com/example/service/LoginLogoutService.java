package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * usersテーブルを操作するサービス.
 * 
 * @author 萩田
 *
 */
@Service
@Transactional
public class LoginLogoutService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * ログイン処理.
	 * 
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ユーザー情報
	 */
	public User login(String email) {
		return userRepository.findByEmail(email);
	}

}
