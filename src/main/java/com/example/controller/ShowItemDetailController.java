package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemDetailService;

/**
 * 商品詳細ページ表示.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/item-detail")
public class ShowItemDetailController {

	@Autowired
	private ShowItemDetailService showItemDetailService;

	/**
	 * 商品詳細ページ表示.
	 * 
	 * @param id 商品id
	 * @return 商品詳細ページ
	 */
	@GetMapping("")
	public String itemDetail(Integer id, Model model) {
		Item item = showItemDetailService.showDetail(id);
		model.addAttribute("itemDetail", item);
		return "detail";
	}

}
