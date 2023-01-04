package com.example.form;

public class UserForm {
	/** ユーザー名 */
	private String name;
	/** パスワード */
	private String password;
	/** メールアドレス */
	private String email;

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

	@Override
	public String toString() {
		return "UserForm [name=" + name + ", password=" + password + ", email=" + email + "]";
	}

}
