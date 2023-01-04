package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * usersレポジトリを操作するサービス.
 * 
 * @author 萩田
 *
 */
@Service
@Transactional
public class InsertUserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザー登録.
	 * 
	 * @param user
	 */
	public void insertUser(User user) {
		// パスワードのハッシュ化
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String hashedPassword = bcpe.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userRepository.insert(user);
	}

}
