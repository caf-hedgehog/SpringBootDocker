package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.service.AddItemService;

/**
 * 商品追加ページ.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/add-item")
public class AddItemController {

	@Autowired
	private ShowItemDetailController showItemDetailController;

	@Autowired
	private AddItemService addItemService;

	/**
	 * 商品追加ページ表示.
	 * 
	 * @return 商品追加ページ
	 */
	@GetMapping("")
	public String index() {
		return "add";
	}

	/**
	 * 商品追加.
	 * 
	 * @return 商品詳細ページ
	 */
	@PostMapping("/create-item")
	public String createItem(ItemForm form, Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setCategory_id(Integer.parseInt(form.getSmall()));
		item.setCondition_id(form.getCondition());
		item = addItemService.insert(item);
		return showItemDetailController.itemDetail(item.getId(), model);
	}

}
