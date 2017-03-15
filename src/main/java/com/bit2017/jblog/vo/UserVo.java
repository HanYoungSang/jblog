package com.bit2017.jblog.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {

	@NotEmpty
	@Length(min=1, max=10)
	private String id;
	
	@NotEmpty
	private String name;
	
//	@Pattern( regexp = "^[0-9]{4,}$")
	@NotEmpty
	private String password;
	
	private String regDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password
				+ ", regDate=" + regDate + "]";
	}
	
	
}
