package com.money.controller.form;

import com.money.model.User;
import javax.validation.constraints.NotNull;

public class UserForm
{
	@NotNull
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String userName;
	@NotNull
	private String password;

	public User converter(){
		return new User(email, name, userName, password);
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}
}
