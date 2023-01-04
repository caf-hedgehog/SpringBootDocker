package com.example.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "train_id", "name", "item_condition_id", "category_name", "brand_name", "price", "shipping",
		"item_description" })
public class TSV {
	private String train_id;
	private String name;
	private String item_condition_id;
	private String category_name;
	private String brand_name;
	private String price;
	private String shipping;
	private String item_description;

	public TSV() {

	}

	public TSV(String train_id, String name, String item_condition_id, String category_name, String brand_name,
			String price, String shipping, String item_description) {
		this.train_id = train_id;
		this.name = name;
		this.item_condition_id = item_condition_id;
		this.category_name = category_name;
		this.brand_name = brand_name;
		this.price = price;
		this.shipping = shipping;
		this.item_description = item_description;
	}

	public String getTrain_id() {
		return train_id;
	}

	public void setTrain_id(String train_id) {
		this.train_id = train_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem_condition_id() {
		return item_condition_id;
	}

	public void setItem_condition_id(String item_condition_id) {
		this.item_condition_id = item_condition_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	@Override
	public String toString() {
		return "Item [train_id=" + train_id + ", name=" + name + ", item_condition_id=" + item_condition_id
				+ ", category_name=" + category_name + ", brand_name=" + brand_name + ", price=" + price + ", shipping="
				+ shipping + ", item_description=" + item_description + "]";
	}

}
