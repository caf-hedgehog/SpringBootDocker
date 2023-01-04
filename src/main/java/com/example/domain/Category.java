package com.example.domain;

public class Category {
	private int id;
	private Integer parent;
	private String name;
	private String nameAll;

	public Category() {

	}

	public Category(int id, Integer parent, String name, String nameAll) {
		super();
		this.id = id;
		this.parent = parent;
		this.name = name;
		this.nameAll = nameAll;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", parent=" + parent + ", name=" + name + ", nameAll=" + nameAll + "]";
	}
}
