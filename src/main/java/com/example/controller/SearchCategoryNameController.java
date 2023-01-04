package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.SearchCategoryNameService;

/**
 * カテゴリ名検索するコントローラ.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/search")
public class SearchCategoryNameController {

	@Autowired
	private SearchCategoryNameService searchCategoryNameService;

	/**
	 * 親カテゴリを検索.
	 * 
	 * @return カテゴリ名
	 */
	@ResponseBody
	@GetMapping("/big")
	public List<String[]> searchBig() {
		List<String[]> categoryName = searchCategoryNameService.searchBigCategory();
		System.out.println(categoryName);
		return categoryName;
	}

	/**
	 * カテゴリ名検索.
	 * 
	 * @param parent 親カテゴリ
	 * @return カテゴリ名
	 */
	@ResponseBody
	@RequestMapping("/searchByParentId")
	public List<String[]> searchByParentId(Integer parent) {
		System.out.println(parent);
		List<String[]> categoryName = searchCategoryNameService.searchByParentId(parent);
		return categoryName;
	}
}
