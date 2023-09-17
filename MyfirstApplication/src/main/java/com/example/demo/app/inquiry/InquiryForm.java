package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//form.htmlの入力値を一括で受け取るクラス=htmlで受け取る値の名前とフィールドの名前が同じであることを確認
public class InquiryForm {
	
	//それぞれに対するフィールドに対する制限
	@Size(min = 1, max = 20, message = "Please input 20characters or less")
	private String name;
	
	@NotNull//空であることを許容しない
	@Email(message = "Invalid E-mail Format")
	private String email;
	
	@NotNull
	private String contents;
	
	public InquiryForm() {
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}


}
