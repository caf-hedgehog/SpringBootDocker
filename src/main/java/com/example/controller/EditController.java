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
import com.example.service.InsertItemService;
import com.example.service.SearchCategoryNameService;
import com.example.service.ShowItemDetailService;

/**
 * 商品情報編集画面.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/item-edit")
public class EditController {

	@Autowired
	private ShowItemDetailController showItemDetailController;

	@Autowired
	private ShowItemDetailService showItemDetailService;

	@Autowired
	private SearchCategoryNameService searchCategoryNameService;

	@Autowired
	private InsertItemService insertItemService;

	/**
	 * 商品情報編集画面表示.
	 * 
	 * @return 商品情報編集ページ
	 */
	@GetMapping("")
	public String index(Integer id, Model model) {
		Item item = showItemDetailService.showDetail(id);
		int smallId = searchCategoryNameService.findByCategoryName(item.getName_all());
		int middleId = searchCategoryNameService.findById(smallId);
		int bigId = searchCategoryNameService.findById(middleId);
		model.addAttribute("smallId", smallId);
		model.addAttribute("middleId", middleId);
		model.addAttribute("bigId", bigId);
		model.addAttribute("item", item);
		return "edit";
	}

	/**
	 * 商品情報編集.
	 * 
	 * @param form  商品情報
	 * @param model リクエストパラメータ
	 * @return 商品詳細ページ
	 */
	@PostMapping("/edit-complete")
	public String edit(ItemForm form, Integer id, Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setId(id);
		item.setCategory_id(Integer.parseInt(form.getSmall()));
		item.setCondition_id(form.getCondition());
		insertItemService.updateItem(item);
		return showItemDetailController.itemDetail(id, model);
	}
}
