package com.money.controller.form;

import com.money.model.User;
import javax.validation.constraints.NotNull;

public class UserForm
{
	@NotNull
	private String email;

	@NotNull
	private String fullName;

	@NotNull
	private String password;

	public User converter(){
		return new User(fullName, email, password);
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public String getFullName()
	{
		return fullName;
	}

	public String getPassword()
	{
		return password;
	}
}
