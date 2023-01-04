package com.example.domain;

import com.example.form.SearchForm;

public class KeepSearchDetail {
	/** 検索フォーム */
	private SearchForm form;
	/** カテゴリ名 */
	private String categoryName;

	public KeepSearchDetail() {
	}

	public KeepSearchDetail(SearchForm form, String categoryName) {
		super();
		this.form = form;
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "KeepSearchDetail [form=" + form + ", categoryName=" + categoryName + "]";
	}

	public SearchForm getForm() {
		return form;
	}

	public void setForm(SearchForm form) {
		this.form = form;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
