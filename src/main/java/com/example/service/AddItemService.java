package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * itemsテーブルを操作するサービス.
 * 
 * @author 萩田
 *
 */
@Transactional
@Service
public class AddItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品の追加.
	 * 
	 * @param item 商品情報
	 * @return 商品情報
	 */
	public Item insert(Item item) {
		item = itemRepository.insert(item);
		return item;
	}
}
