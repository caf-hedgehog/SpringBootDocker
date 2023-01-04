package com.example.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Big;
import com.example.domain.Category;
import com.example.domain.CategoryName;
import com.example.domain.Middle;
import com.example.domain.Small;
import com.example.repository.CategoryRepository;
import com.example.repository.OriginalRepository;

/**
 * categoryテーブルを操作するサービス.
 * 
 * @author 萩田
 *
 */
@Service
@Transactional
public class InsertCategoryService {

	@Autowired
	private OriginalRepository originalRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * カテゴリ名を抽出してcategoryテーブルに挿入する.
	 * 
	 */
	public void getCategoryName() {
		// ユニークカテゴリの名前を取得
		List<String> categoryNameList = originalRepository.findByUniqueCategoryName();

		// ユニーク親カテゴリ
		Set<String> oya = new HashSet<>();
		// ユニーク子カテゴリ
		Set<String> child = new HashSet<>();

		// 全部入ってるやつ
		List<CategoryName> categoryList = new ArrayList<>();
		for (int i = 0; i < categoryNameList.size(); i++) {
			try {
				String categoryNames[] = categoryNameList.get(i).split("/");

				// ユニーク数計算
				oya.add(categoryNames[0]);
				child.add(categoryNames[1]);

				categoryList.add(new CategoryName(categoryNames[0], categoryNames[1], categoryNames[2],
						categoryNameList.get(i)));

			} catch (Exception e) {

			}
		}
		// 親カテゴリ名
		List<Big> bigs = new ArrayList<>();
		for (String tmp : oya) {
			bigs.add(new Big(tmp, new ArrayList<>()));
		}
		for (int i = 0; i < bigs.size(); i++) {
			int count = 0;
			for (int j = 0; j < categoryList.size(); j++) {
				// 大項目が一致した時
				if (bigs.get(i).getCategoryName().equals(categoryList.get(j).getBig())) {
					// middleのlistを持っていなければnewしてsmallも挿入
					if (bigs.get(i).getMiddle() == null) {
						bigs.get(i).getMiddle().add(count,
								new Middle(categoryList.get(j).getMiddle(), new ArrayList<>()));
						bigs.get(i).getMiddle().get(count).getSmall()
								.add(new Small(categoryList.get(j).getSmall(), categoryList.get(j).getPath()));
						count++;
					} else {
						boolean check = true;
						// リスト内にmiddleが一致するものを持ってたらそこにsmallを追加
						for (int k = 0; k < bigs.get(i).getMiddle().size(); k++) {
							if (bigs.get(i).getMiddle().get(k).getMiddle().equals(categoryList.get(j).getMiddle())) {
								bigs.get(i).getMiddle().get(k).getSmall()
										.add(new Small(categoryList.get(j).getSmall(), categoryList.get(j).getPath()));
								check = false;
							}
						}
						// 一致するmiddleが無ければ新たにnewしてsmall挿入
						if (check) {
							bigs.get(i).getMiddle().add(count,
									new Middle(categoryList.get(j).getMiddle(), new ArrayList<>()));
							bigs.get(i).getMiddle().get(count).getSmall()
									.add(new Small(categoryList.get(j).getSmall(), categoryList.get(j).getPath()));
							count++;
						}
					}
				}
			}
		}
		// 空の要素を削除
		for (int i = 0; i < bigs.size(); i++) {
			if (bigs.get(i).getMiddle().size() == 0) {
				bigs.remove(i);
			}
		}

		for (Big big : bigs) {
			Category bigObj = new Category();
			bigObj.setName(big.getCategoryName());
			Integer bigID = categoryRepository.insert(bigObj);
			for (Middle middle : big.getMiddle()) {
				Category middleObj = new Category();
				middleObj.setName(middle.getMiddle());
				middleObj.setParent(bigID);
				Integer middleId = categoryRepository.insert(middleObj);
				for (Small small : middle.getSmall()) {
					Category smallObj = new Category();
					smallObj.setParent(middleId);
					smallObj.setName(small.getSmall());
					smallObj.setNameAll(small.getPath());
					categoryRepository.insert(smallObj);
				}
			}
			System.out.println(big.getCategoryName() + "インサート完了");
		}
	}
}
