package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

/**
 * userテーブルを操作するコントローラ.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("")
public class LoginLogoutController {

	@Autowired
	private HttpSession session;
	
	/**
	 * ログイン画面表示.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/login")
	public String index() {
		return "login";
	}

	/**
	 * ログイン処理.
	 * 
	 * @param form ユーザーフォーム
	 * @return 商品リストページ
	 */
	@PostMapping("/submit")
	public String login() {
		return "/item-list";
	}

	/**
	 * ログアウト処理.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute("username");
		return "login";
	}

}
