package com.example.domain;

public class User {
	/** ID */
	private Integer id;
	/** ユーザー名 */
	private String name;
	/** パスワード */
	private String password;
	/** メールアドレス */
	private String email;
	/** 権限 */
	private Integer authority;

	public User() {
	}

	public User(Integer id, String name, String password, String email, Integer authority) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.authority = authority;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", authority="
				+ authority + "]";
	}

}
