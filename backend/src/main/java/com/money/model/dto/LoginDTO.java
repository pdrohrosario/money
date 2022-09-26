package com.money.model.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO
{
	private String userName;
	private String password;

	public String getPassword()
	{
		return password;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken converter()
	{
		return new UsernamePasswordAuthenticationToken(userName, password);
	}
}
