package com.example.domain;

import java.util.List;

public class Big {
	private String categoryName;
	private List<Middle> middle;

	public Big() {

	}

	public Big(String categoryName, List<Middle> middle) {
		super();
		this.categoryName = categoryName;
		this.middle = middle;
	}

	@Override
	public String toString() {
		return "Category [categoryName=" + categoryName + ", middle=" + middle + "]";
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Middle> getMiddle() {
		return middle;
	}

	public void setMiddle(List<Middle> middle) {
		this.middle = middle;
	}

}
