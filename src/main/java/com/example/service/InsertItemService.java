package com.example.service;

import java.util.List;

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
public class InsertItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品全検索.
	 * 
	 * @return 商品リスト
	 */
	public List<Item> findAll() {
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}

	/**
	 * 商品情報編集.
	 * 
	 * @param item 商品情報
	 */
	public void updateItem(Item item) {
		itemRepository.update(item);
	}

}
