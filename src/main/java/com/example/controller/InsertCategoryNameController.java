package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.InsertCategoryService;

/**
 * カテゴリDB登録.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/category-name")
public class InsertCategoryNameController {

	@Autowired
	private InsertCategoryService insertCategoryService;

	/**
	 * カテゴリ名登録.
	 * 
	 * @return 適当なページ
	 */
	@GetMapping("")
	public String getCategoryName() {
		insertCategoryService.getCategoryName();
		return "smaple";
	}

}
