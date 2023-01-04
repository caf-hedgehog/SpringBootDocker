package com.example.form;

public class SearchForm {
	/** 商品名 */
	private String name;
	/** 大項目 */
	private String big;
	/** 中項目 */
	private String middle;
	/** 小項目 */
	private String small;
	/** ブランド */
	private String brand;

	public SearchForm() {
	}

	public SearchForm(String name, String big, String middle, String small, String brand) {
		super();
		this.name = name;
		this.big = big;
		this.middle = middle;
		this.small = small;
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "SearchForm [name=" + name + ", big=" + big + ", middle=" + middle + ", small=" + small + ", brand="
				+ brand + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
