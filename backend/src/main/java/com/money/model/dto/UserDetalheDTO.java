package com.money.model.dto;

import com.money.model.User;
import javax.validation.constraints.NotNull;

public class UserDetalheDTO
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
	public String getEmail()
	{
		return email;
	}
	public String getName()
	{
		return name;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password;
	}
}
