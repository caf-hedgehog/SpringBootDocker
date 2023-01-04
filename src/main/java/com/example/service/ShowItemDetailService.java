package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.CategoryRepository;
import com.example.repository.ItemRepository;

/**
 * itemsテーブルを操作するサービス.
 * 
 * @author 萩田
 *
 */
@Transactional
@Service
public class ShowItemDetailService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 商品詳細.
	 * 
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item showDetail(Integer id) {
		Item item = itemRepository.findById(id);
		item.setSmall(categoryRepository.findByCategoryName(item.getName_all()).toString());
		return item;
	}

}
