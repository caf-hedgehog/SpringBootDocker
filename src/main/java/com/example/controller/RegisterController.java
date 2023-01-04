package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.service.InsertUserService;

/**
 * ユーザー登録処理.
 * 
 * @author 萩田
 *
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private InsertUserService insertUserService;

	/**
	 * ユーザー登録画面表示.
	 * 
	 * @return ユーザー登録画面
	 */
	@GetMapping("")
	public String index() {
		return "register";
	}

	/**
	 * ユーザー登録処理.
	 * 
	 * @return ログイン画面
	 */
	@PostMapping("/save-user")
	public String register(RegisterUserForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setAuthority(1);
		insertUserService.insertUser(user);
		return "login";
	}

}
