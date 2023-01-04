package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.CategoryRepository;

/**
 * categoryテーブルを操作するサービス.
 * 
 * @author 萩田
 *
 */
@Transactional
@Service
public class SearchCategoryNameService {

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * カテゴリ名を検索.
	 * 
	 * @return カテゴリ名
	 */
	public List<String[]> searchBigCategory() {
		List<String[]> categoryNameList = categoryRepository.searchBigCategory();
		return categoryNameList;
	}

	/**
	 * カテゴリ名を検索.
	 * 
	 * @return カテゴリ名
	 */
	public List<String[]> searchByParentId(Integer parent) {
		List<String[]> categoryNameList = categoryRepository.searchByParentId(parent);
		return categoryNameList;
	}

	/**
	 * 親IDを検索.
	 * 
	 * @param id カテゴリID
	 * @return 親ID
	 */
	public Integer findById(Integer id) {
		return categoryRepository.findById(id);
	}

	/**
	 * 小項目のIDを検索.
	 * 
	 * @param categoryName カテゴリ名
	 * @return 小項目ID
	 */
	public Integer findByCategoryName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}

	/**
	 * カテゴリ名検索.
	 * 
	 * @param id カテゴリID
	 * @return カテゴリ名
	 */
	public String searchCategoryName(Integer id) {
		return categoryRepository.findByCategoryID(id);
	}
}
