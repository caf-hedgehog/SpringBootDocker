package com.example.domain;

public class Item {
	/** 商品id */
	private Integer id;
	/** 商品名 */
	private String name;
	/** コンディションid */
	private Integer condition_id;
	/** カテゴリID */
	private Integer category_id;
	/** カテゴリ */
	private String name_all;
	/** 大項目 */
	private String big;
	/** 中項目 */
	private String middle;
	/** 小項目 */
	private String small;
	/** ブランド名 */
	private String brand;
	/** 金額 */
	private double price;
	/** 運送id */
	private Integer shipping;
	/** 商品説明 */
	private String description;

	public Item() {
	}

	public Item(Integer id, String name, Integer condition_id, Integer category_id, String name_all, String big,
			String middle, String small, String brand, double price, Integer shipping, String description) {
		super();
		this.id = id;
		this.name = name;
		this.condition_id = condition_id;
		this.category_id = category_id;
		this.name_all = name_all;
		this.big = big;
		this.middle = middle;
		this.small = small;
		this.brand = brand;
		this.price = price;
		this.shipping = shipping;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition_id() {
		return condition_id;
	}

	public void setCondition_id(Integer condition_id) {
		this.condition_id = condition_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getName_all() {
		return name_all;
	}

	public void setName_all(String name_all) {
		this.name_all = name_all;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
