package com.example.domain;

import java.util.List;

public class Middle {
	private String middle;
	private List<Small> small;

	public Middle() {

	}

	public Middle(String middle, List<Small> small) {
		super();
		this.middle = middle;
		this.small = small;
	}

	@Override
	public String toString() {
		return "Middle [middle=" + middle + ", small=" + small + "]";
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public List<Small> getSmall() {
		return small;
	}

	public void setSmall(List<Small> small) {
		this.small = small;
	}

	
}
