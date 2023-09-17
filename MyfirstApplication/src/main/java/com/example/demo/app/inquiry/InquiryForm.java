package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//form.html�̓��͒l���ꊇ�Ŏ󂯎��N���X=html�Ŏ󂯎��l�̖��O�ƃt�B�[���h�̖��O�������ł��邱�Ƃ��m�F
public class InquiryForm {
	
	//���ꂼ��ɑ΂���t�B�[���h�ɑ΂��鐧��
	@Size(min = 1, max = 20, message = "Please input 20characters or less")
	private String name;
	
	@NotNull//��ł��邱�Ƃ����e���Ȃ�
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
