package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.KeepSearchDetail;
import com.example.form.SearchForm;
import com.example.service.SearchCategoryNameService;
import com.example.service.ShowItemListService;

import jakarta.servlet.http.HttpSession;

/**
 * 商品リスト.
 * 
 * @author 萩田
 *
 */
@Controller
@RequestMapping("/item-list")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	@Autowired
	private SearchCategoryNameService searchCategoryNameService;

	@Autowired
	private HttpSession session;

	/**
	 * 商品リストページ表示.
	 * 
	 * @return 商品リストページ
	 */
	@RequestMapping("")
	public String index(@AuthenticationPrincipal UserDetails user, Integer id, Integer pageNum, Model model) {
		session.setAttribute("username", user.getUsername());
		System.out.println(user.getUsername());
		// 最初のページで0から表示させる
		if (id == null) {
			id = 0;
		}

		// ページ数指定があればIDに代入
		if (pageNum != null) {
			id = (pageNum - 1) * 30;
		} else {
			// ページ数の初期化
			pageNum = 1;
		}

		List<Item> itemList = new ArrayList<>();

		// 検索値が存在する場合の処理
		if (session.getAttribute("searchDetail") != null) {
			KeepSearchDetail searchDetail = (KeepSearchDetail) session.getAttribute("searchDetail");
			itemList = showItemListService.searchItem(searchDetail.getForm(), id, searchDetail.getCategoryName());
		} else {
			itemList = showItemListService.showItem(id);
			Integer maxPage = calcPage(showItemListService.countPage());
			model.addAttribute("maxPage", maxPage);
		}

		model.addAttribute("itemID", itemList.get(0).getId());
		model.addAttribute("itemList", itemList);
		model.addAttribute("pageNum", pageNum);
		return "list";
	}

	/**
	 * 商品検索機能.
	 * 
	 * @param form  フォーム
	 * @param model リクエストパラメータ
	 * @param id    商品ID
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/search")
	public String itemList(SearchForm form, Model model, Integer id, Integer pageNum) {
		String categoryName = "";

		if (id == null) {
			id = 0;
		}
		if (form.getBig() == null) {
			form.setBig("0");
		}
		if (form.getMiddle() == null) {
			form.setMiddle("0");
		}
		if (form.getSmall() == null) {
			form.setSmall("0");
		}
		if (form.getName() == null) {
			form.setName("");
		}
		if (form.getBrand() == null) {
			form.setBrand("");
		}

		if (!form.getBig().equals("0")) {
			try {
				categoryName += searchCategoryNameService.searchCategoryName(Integer.parseInt(form.getBig())) + "/";
			} catch (Exception e) {
				categoryName += form.getBig();
			}
		}
		if (!form.getMiddle().equals("0")) {
			try {
				categoryName += searchCategoryNameService.searchCategoryName(Integer.parseInt(form.getMiddle())) + "/";
			} catch (Exception e) {
				categoryName += "/" + form.getMiddle();
			}
		}
		if (!form.getSmall().equals("0")) {
			try {
				categoryName += searchCategoryNameService.searchCategoryName(Integer.parseInt(form.getSmall()));
			} catch (Exception e) {
				categoryName += "/" + form.getSmall();
			}
		}
		List<Item> itemList = showItemListService.searchItem(form, id, categoryName);
		int maxPage = calcPage(showItemListService.countSerch(form, id, categoryName));
		model.addAttribute("itemList", itemList);
		model.addAttribute("itemID", itemList.get(0).getId());
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("pageNum", id + 1);

		KeepSearchDetail searchDetail = new KeepSearchDetail(form, categoryName);
		session.setAttribute("searchDetail", searchDetail);
		session.setAttribute("maxPage", maxPage);
		return "list";
	}

	/**
	 * ページ数計算.
	 * 
	 * @param listSize リストサイズ
	 * @return ページ全体数
	 */
	public Integer calcPage(Integer listSize) {
		double calc = listSize / 30;
		if (listSize % 30 != 0) {
			calc += 1;
		}
		return (int) calc;
	}

}
