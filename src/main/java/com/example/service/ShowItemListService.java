package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.form.SearchForm;
import com.example.repository.ItemRepository;

/**
 * itemsテーブルを操作するサービス.
 * 
 * @author 萩田.
 *
 */
@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品全検索.
	 * 
	 * @return 商品情報
	 */
	public List<Item> showItem(Integer id) {
		List<Item> itemList = itemRepository.findByPageId(id);
		return itemList;
	}

	/**
	 * ページ数検索.
	 * 
	 * @return ページ数
	 */
	public Integer countPage() {
		return itemRepository.countById();
	}

	public Integer countSerch(SearchForm form, Integer id, String categoryName) {
		return itemRepository.countSearch(form, id, categoryName);
	}

	/**
	 * 商品検索.
	 * 
	 * @param form         商品検索条件
	 * @param id           商品ID
	 * @param categoryName 商品カテゴリ名
	 * @return 商品情報
	 */
	public List<Item> searchItem(SearchForm form, Integer id, String categoryName) {
		List<Item> itemList = itemRepository.findBySearchForm(form, id, categoryName);
		return itemList;

	}
}
