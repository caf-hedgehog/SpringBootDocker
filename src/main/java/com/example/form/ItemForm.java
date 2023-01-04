package com.example.form;

public class ItemForm {
	/** 商品名 */
	private String name;
	/** 金額 */
	private double price;
	/** 大項目 */
	private String big;
	/** 中項目 */
	private String middle;
	/** 小項目 */
	private String small;
	/** ブランド名 */
	private String brand;
	/** コンディションid */
	private Integer condition;
	/** 商品説明 */
	private String description;

	public ItemForm() {
	}

	public ItemForm(String name, double price, String big, String middle, String small, String brand, Integer condition,
			String description) {
		super();
		this.name = name;
		this.price = price;
		this.big = big;
		this.middle = middle;
		this.small = small;
		this.brand = brand;
		this.condition = condition;
		this.description = description;
	}

	@Override
	public String toString() {
		return "ItemForm [name=" + name + ", price=" + price + ", big=" + big + ", middle=" + middle + ", small="
				+ small + ", brand=" + brand + ", condition=" + condition + ", description=" + description + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
