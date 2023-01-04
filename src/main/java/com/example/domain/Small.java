package com.example.domain;

public class Small {
	private String small;
	private String path;

	public Small() {

	}

	public Small(String small, String path) {
		super();
		this.small = small;
		this.path = path;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Detail [small=" + small + ", path=" + path + "]";
	}

}
