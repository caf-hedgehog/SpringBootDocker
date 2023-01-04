package com.example.form;

public class RegisterUserForm {
	/** ユーザー名 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 確認用パスワード */
	private String confirm;

	public RegisterUserForm() {
	}

	public RegisterUserForm(String name, String email, String password, String confirm) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "RegisterUserForm [name=" + name + ", email=" + email + ", password=" + password + ", confirm=" + confirm
				+ "]";
	}

}
